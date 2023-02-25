package com.friendly.framework.feature.cart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.repository.CartRepository
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
    val gson = Gson()

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    val cartProducts: LiveData<List<Product>>
        get() = cartRepository.cartProducts

    val cart: LiveData<JSONObject>
        get() = cartRepository.cart

    val cartCount: LiveData<Int>
        get() = cartRepository.cartCount

    val mrp: LiveData<Float>
        get() = cartRepository.mrp

    val discount: LiveData<Float>
        get() = cartRepository.discount

    val subtotal: LiveData<Float>
        get() = cartRepository.subtotal

    val tax: LiveData<Float>
        get() = cartRepository.tax

    val instantDiscount: LiveData<Float>
        get() = cartRepository.instantDiscount

    val totalAmount: LiveData<Float>
        get() = cartRepository.totalAmount


    fun updateProductsInCart() {
        var productsAddedToCart: ArrayList<Product> = arrayListOf()
        ProductHandler.shared().repository.allProduct.value?.forEach {
            if (cart.value?.has(it.Id) == true) {
                productsAddedToCart.add(it)
            }
        }
        cartRepository.cartProductLiveData.postValue(productsAddedToCart)
        updatePricesInCart()
    }

    fun updateInstantDiscount(amount: Float) {
        cartRepository?.instantDiscountLiveData?.postValue(amount)
    }

    fun updatePricesInCart() {
        var totalMrp : Float = 0F
        var priceDiscount: Float = 0F
        var priceSubtotal = 0F
        var priceTax = 0F
        var priceTotal = 0F
        var instantDiscountPrice = 0F
        cartProducts.value?.forEach {
            val quanity = getProductQuantity(it)
            if (it.Discount != null) {
                priceDiscount = priceDiscount + (it.Discount!! * quanity)
            }
            if (it.FinalPrice != null) {
                priceSubtotal = priceSubtotal + (it.FinalPrice!! * quanity)
            }
            if (it.MRP != null) {
                totalMrp = totalMrp + (it.MRP!! * quanity)
            }
            if (it.Tax != null) {
                if (it.TaxIncluded != null) {
                    if (!it.TaxIncluded!!) {
                        priceTax = priceTax + (it.Tax!! * quanity)
                    }
                } else {
                    priceTax = priceTax + (it.Tax!! * quanity)
                }
            }
        }
        if (instantDiscount.value != null) {
            instantDiscountPrice = instantDiscount.value!!
        }
        priceSubtotal = (priceSubtotal + priceTax)
        priceTotal = priceSubtotal - instantDiscountPrice
        cartRepository.subtotalLiveData.postValue(priceSubtotal)
        cartRepository.discountLiveData.postValue(priceDiscount)
        cartRepository.taxLiveData.postValue(priceTax)
        cartRepository.totalAmountLiveData.postValue(priceTotal)
        cartRepository.mrpLiveData.postValue(totalMrp)
    }

    fun updateCount() {
        var count = 0
        cart.value?.keys()?.forEach {
            count += 1
        }
        cartRepository.cartCountLiveData.postValue(count)
    }

    fun getProductQuantity(product: Product): Int {
        var quanity = 0
        if (cart.value != null && cart.value!!.has(product.Id)) {
            quanity = cart.value!!.getInt(product.Id!!)
        }
        return quanity
    }


    fun resetCart() {
        cartRepository.cartProductLiveData.postValue(arrayListOf())
        cartRepository.cartLiveData.postValue(JSONObject())
        cartRepository.cartCountLiveData.postValue(0)
        cartRepository.discountLiveData.postValue(0F)
        cartRepository.subtotalLiveData.postValue(0F)
        cartRepository.taxLiveData.postValue(0F)
        cartRepository.instantDiscountLiveData.postValue(0F)
        cartRepository.totalAmountLiveData.postValue(0F)
    }

    fun createSaleAndGenerateReceipt(customer: Customer?) {
        val transactions = prepareTransaction(customer)
        if (transactions.count() == 0) {
            return
        }
        var request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business!!.value!!.Id)
        if(CustomerHandler.shared().repository.customer.value !=null){
            val customerJson = gson.toJson(CustomerHandler.shared().repository.customer.value)
            request.put(KeyConstant.customer, JSONObject(customerJson))
        }
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.transactions, JSONArray(transactions))
        SocketService.shared().send(SocketEvent.CREATE_SALE, request)
    }

    fun createInvoice(sales: ArrayList<JSONObject>, salesIds: ArrayList<String>,customer: Customer?) {
        val unixTime = System.currentTimeMillis()
        val transactions = prepareTransaction(customer)
        if (transactions.count() == 0) {
            return
        }
        var request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business.value
        request.put(KeyConstant.invoiceId,unixTime)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business?.Id)
        request.put(KeyConstant.transactions, JSONArray(transactions))
        request.put(KeyConstant.sales, JSONArray(sales))
        request.put(KeyConstant.salesID, JSONArray(salesIds))
        request.put(KeyConstant.business, JSONObject(gson.toJson(business)))
        request.put(KeyConstant.instantDiscount, instantDiscount.value)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        if(CustomerHandler.shared().repository.customer.value !=null){
            val customerJson = gson.toJson(CustomerHandler.shared().repository.customer.value)
            request.put(KeyConstant.customer, JSONObject(customerJson))
        }

        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.GENERATE_CUSTOMER_INVOICE, request)
    }

    fun prepareTransaction(customer: Customer?): ArrayList<JSONObject> {
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        var transactions: ArrayList<JSONObject> = arrayListOf()
        if (cartProducts.value != null && !cartProducts.value!!.isEmpty()) {
            cartProducts.value?.forEach {
                val gson = Gson()
                val productData = JSONObject(gson.toJson(it))
                val transactionData = JSONObject()
                transactionData.put(KeyConstant.userId, user._id)
                transactionData.put(KeyConstant.businessID, business!!.value!!.Id)
                if(customer != null){
                    transactionData.put(KeyConstant.customerID,customer.Id)
                    transactionData.put(KeyConstant.customerName,customer.Name)
                    transactionData.put(KeyConstant.customerMobile,customer.MobileNumber)
                    transactionData.put(KeyConstant.vehicleNumber,customer.DeviceID)
                }
                val quanity = getProductQuantity(it)
                var IGST = 0
                var CGST = 0
                var SGST = 0
                var VAT = 0
                var CESS = 0
                var Tax = 0
                if (it.IGST != null) {
                    IGST = it.IGST!!.toInt() * quanity
                }
                if (it.CGST != null) {
                    CGST = it.CGST!!.toInt() * quanity
                }
                if (it.SGST != null) {
                    SGST = it.SGST!!.toInt() * quanity
                }
                if (it.VAT != null) {
                    VAT = it.VAT!!.toInt() * quanity
                }
                if (it.CESS != null) {
                    CESS = it.CESS!!.toInt() * quanity
                }
                if (it.Tax != null) {
                    Tax = it.Tax!!.toInt() * quanity
                }
                transactionData.put(KeyConstant.productID, it.Id)
                transactionData.put(KeyConstant.productName, it.Name)
                transactionData.put(KeyConstant.product, productData)
                transactionData.put(KeyConstant.mode, "CASH")
                transactionData.put(KeyConstant.quantity, quanity)
                transactionData.put(KeyConstant.IGST, IGST)
                transactionData.put(KeyConstant.CGST, CGST)
                transactionData.put(KeyConstant.SGST, SGST)
                transactionData.put(KeyConstant.VAT, VAT)
                transactionData.put(KeyConstant.CESS, CESS)
                transactionData.put(KeyConstant.tax, Tax)
                transactionData.put(KeyConstant.price, it.Price)
                transactionData.put(KeyConstant.finalPrice, it.FinalPrice?.times(quanity) ?: it.FinalPrice)
                transactionData.put(KeyConstant.costPrice, it.CostPrice)
                transactionData.put(KeyConstant.saleDate, now())
                transactionData.put(KeyConstant.discount, it.Discount)
                transactionData.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
                if(customer != null && customer.Id != null && !customer.Id!!.isEmpty()){
                    transactionData.put(KeyConstant.customerName, customer.Name)
                    transactionData.put(KeyConstant.customerMobile, customer.MobileNumber)
                    transactionData.put(KeyConstant.customerID, customer.Id)
                }
                transactions.add(transactionData)
            }
        }
        return transactions
    }

    fun now(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }
}