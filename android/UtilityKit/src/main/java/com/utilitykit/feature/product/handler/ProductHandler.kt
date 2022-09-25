package com.utilitykit.feature.product.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.feature.product.viewModel.ProductViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductHandler {

    var productViewModel: ProductViewModel? = null
    val repository = ProductRepository()
    var activity : UtilityActivity? = null
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: ProductHandler? = null
        fun shared() : ProductHandler {
            if(instance != null){
                return instance as ProductHandler
            }else{
                return ProductHandler()
            }
        }
    }

    fun setup(model:ProductViewModel){
        productViewModel = model
    }


    fun fetchAllProduct(){
        val request = JSONObject()
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            val business = BusinessHandler.shared().repository.business
            request.put(Key.userId,user._id)
            request.put(Key.businessID,business!!.Id)
            SocketManager.send(SocketEvent.RETRIVE_PRODUCT,request)
        }
    }

     val retriveProduct = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                var allProduct : ArrayList<Product> = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val product = gson.fromJson(item.toString(),Product::class.java)
                    allProduct.add(product)
                }
                repository.productLiveData.postValue(allProduct)
            }
        }
    }

    val onCreateProduct = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    val product = gson.fromJson(payload.toString(),Product::class.java)
                    repository.newProductLiveData.postValue(product)
                    fetchAllProduct()
                    activity?.runOnUiThread {
                        activity?.toast("Product created Successfully")
                    }
                }else{
                    activity?.runOnUiThread {
                        activity?.toast("Product couldn't be created, please try after some time")
                    }
                }
            }else{
                activity?.runOnUiThread {
                    activity?.toast("Product couldn't be created, please try after some time")
                }
            }
        }
    }



}