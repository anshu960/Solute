package com.utilitykit.feature.cart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityKitApp.Companion.user
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.repository.CartRepository
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business!!.Id)
        if(CustomerHandler.shared().repository.customer.value !=null && CustomerHandler.shared().repository.customer.value!!.Id !=null){
            val customerJson = gson.toJson(CustomerHandler.shared().repository.customer.value)
            request.put(Key.customer, JSONObject(customerJson))
        }
        request.put(Key.transactions, JSONArray(transactions))
        SocketManager.send(SocketEvent.CREATE_SALE, request)
    }

    fun createInvoice(sales: ArrayList<JSONObject>, salesIds: ArrayList<String>,customer: Customer?) {
        val transactions = prepareTransaction(customer)
        if (transactions.count() == 0) {
            return
        }
        var request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business!!.Id)
        request.put(Key.transactions, JSONArray(transactions))
        request.put(Key.sales, JSONArray(sales))
        request.put(Key.salesID, JSONArray(salesIds))
        request.put(Key.business, JSONObject(gson.toJson(business)))
        request.put(Key.instantDiscount, instantDiscount.value)
        if(CustomerHandler.shared().repository.customer.value !=null && CustomerHandler.shared().repository.customer.value!!.Id !=null){
            val customerJson = gson.toJson(CustomerHandler.shared().repository.customer.value)
            request.put(Key.customer, JSONObject(customerJson))
        }
        SocketManager.send(SocketEvent.GENERATE_CUSTOMER_INVOICE, request)
    }

    fun prepareTransaction(customer: Customer?): ArrayList<JSONObject> {
        val business = BusinessHandler.shared().repository.business
        var transactions: ArrayList<JSONObject> = arrayListOf()
        if (cartProducts.value != null && !cartProducts.value!!.isEmpty()) {
            cartProducts.value?.forEach {
                val gson = Gson()
                val productData = JSONObject(gson.toJson(it))
                val transactionData = JSONObject()
                transactionData.put(Key.userId, user._id)
                transactionData.put(Key.businessID, business!!.Id)
                if(customer != null){
                    transactionData.put(Key.customerID,customer.Id)
                    transactionData.put(Key.customerName,customer.Name)
                    transactionData.put(Key.customerMobile,customer.MobileNumber)
                    transactionData.put(Key.vehicleNumber,customer.DeviceID)
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
                transactionData.put(Key.productID, it.Id)
                transactionData.put(Key.productName, it.Name)
                transactionData.put(Key.product, productData)
                transactionData.put(Key.mode, "CASH")
                transactionData.put(Key.quantity, quanity)
                transactionData.put(Key.IGST, IGST)
                transactionData.put(Key.CGST, CGST)
                transactionData.put(Key.SGST, SGST)
                transactionData.put(Key.VAT, VAT)
                transactionData.put(Key.CESS, CESS)
                transactionData.put(Key.tax, Tax)
                transactionData.put(Key.price, it.Price)
                transactionData.put(Key.finalPrice, it.FinalPrice?.times(quanity) ?: it.FinalPrice)
                transactionData.put(Key.costPrice, it.CostPrice)
                transactionData.put(Key.saleDate, now())
                transactionData.put(Key.discount, it.Discount)
                if(customer != null && customer.Id != null && !customer.Id!!.isEmpty()){
                    transactionData.put(Key.customerName, customer.Name)
                    transactionData.put(Key.customerMobile, customer.MobileNumber)
                    transactionData.put(Key.customerID, customer.Id)
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