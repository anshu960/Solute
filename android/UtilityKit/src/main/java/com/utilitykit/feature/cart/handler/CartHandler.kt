package com.utilitykit.feature.cart.handler

import android.content.Intent
import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.TableNames.Companion.sale
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.cart.repository.CartRepository
import com.utilitykit.feature.cart.viewModel.CartViewModel
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.invoice.handler.InvoiceHandler
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.feature.product.model.Product
import io.socket.emitter.Emitter
import org.json.JSONObject

class CartHandler {

    var viewModel: CartViewModel? = null
    val repository = CartRepository()
    var activity = UtilityActivity()
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: CartHandler? = null
        fun shared() : CartHandler {
            if(instance != null){
                return instance as CartHandler
            }else{
                return CartHandler()
            }
        }
    }

    val createSale = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                var allSale : ArrayList<JSONObject> = arrayListOf()
                var salesIds : ArrayList<String> = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val newSale = payload.getJSONObject(i)
                    if(newSale.has(Key._id)){
                        salesIds.add(newSale.getString(Key._id))
                        val saleObj = gson.fromJson(newSale.toString(), Sale::class.java)
                        InvoiceHandler.shared().viewModel?.insertSale(saleObj)
                    }
                    allSale.add(newSale)
                }
                if(allSale.count() > 0){
                    viewModel?.createInvoice(allSale,salesIds,CustomerHandler.shared().repository.customer.value)
                }
            }
        }
    }

    val createCustomerInvoice = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                val allSalesData = anyData.getJSONArray(Key.sales)
                val customerInvoice = gson.fromJson(payload.toString(), CustomerInvoice::class.java)
                InvoiceHandler.shared().viewModel?.insert(customerInvoice)
                CustomerHandler.shared().repository.customerLiveData.postValue(null)
                CustomerHandler.shared().onCreateNewCustomer = null
                BusinessHandler.shared().activity.runOnUiThread {
                    if(payload.has(Key._id) && allSalesData.length() > 0){
                        InvoiceHandler.shared().onCreateNewCustomerInvoiceResponse?.let { it1 -> it1(anyData) }
                    }
                }
            }
        }
    }

    fun setup(model:CartViewModel){
        viewModel = model
    }

    fun addToCart(product: Product){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem!!.has(product.Id)){
                var quantity = allCartItem.getInt(product.Id)
                quantity+=1
                allCartItem.put(product.Id,quantity)
            }else{
                allCartItem.put(product.Id,1)
            }
            repository.cartLiveData.postValue(allCartItem)
        }else{
            val newCart = JSONObject()
            newCart.put(product.Id,1)
            repository.cartLiveData.postValue(newCart)
        }
        viewModel?.updateCount()
        viewModel?.updateProductsInCart()
    }

    fun removeFromCart(product: Product){
        var allCartItem = repository.cartLiveData.value
        if (allCartItem != null){
            if(allCartItem!!.has(product.Id)){
                var quantity = allCartItem.getInt(product.Id)
                quantity-=1
                if(quantity<1){
                 allCartItem.remove(product.Id)
                }else{
                    allCartItem.put(product.Id,quantity)
                }
            }
            repository.cartLiveData.postValue(allCartItem)
        }
        viewModel?.updateCount()
        viewModel?.updateProductsInCart()
    }
}