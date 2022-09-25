package com.utilitykit.feature.productCategory.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModel
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.repository.ProductCategoryRepository
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductCategoryHandler{

    var productCategoryViewModel: ProductCategoryViewModel? = null
    val repository = ProductCategoryRepository()
    var activity = UtilityActivity()
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: ProductCategoryHandler? = null
        fun shared() : ProductCategoryHandler {
            if(instance != null){
                return instance as ProductCategoryHandler
            }else{
                return ProductCategoryHandler()
            }
        }
    }

    fun setup(model: ProductCategoryViewModel){
        productCategoryViewModel = model
    }


    fun fetchAllProductCategory(){
        val request = JSONObject()
        val user = User()
        if( BusinessHandler.shared().repository.business != null){
            val business = BusinessHandler.shared().repository.business
            request.put(Key.userId,user._id)
            request.put(Key.businessID,business!!.Id)
            SocketManager.send(SocketEvent.RETRIVE_PRODUCT_CATEGORY,request)
        }
    }

     val retriveProductCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                var allProductCategory : ArrayList<ProductCategory> = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val product = gson.fromJson(item.toString(),ProductCategory::class.java)
                    allProductCategory.add(product)
                }
                repository.categoryLiveData.postValue(allProductCategory)
            }
        }
    }

    val onCreateProductCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    activity.runOnUiThread {
                        activity.toast("Category Created Successfully")
                        activity.onBackPressed()
                    }
                }
            }
        }
    }

}