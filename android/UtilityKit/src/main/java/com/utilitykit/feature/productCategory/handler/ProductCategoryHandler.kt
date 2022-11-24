package com.utilitykit.feature.productCategory.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.socket.SocketEvent
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.repository.ProductCategoryRepository
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductCategoryHandler{

    var productCategoryViewModel: ProductCategoryViewModel? = null
    val repository = ProductCategoryRepository()
    var activity = UtilityActivity()
    var onCreateNewCategory : ((category: ProductCategory)->Unit)? = null
    var onSelectCategory : ((category: ProductCategory)->Unit)? = null
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
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId,user._id)
        request.put(Key.businessID, business.value?.Id)
        SocketService.shared().send(SocketEvent.RETRIVE_PRODUCT_CATEGORY,request)
    }

     val retriveProductCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val category = gson.fromJson(item.toString(),ProductCategory::class.java)
                    productCategoryViewModel?.insert(category)
                }
            }
        }
    }

    val onCreateProductCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                val category = gson.fromJson(payload.toString(),ProductCategory::class.java)
                productCategoryViewModel?.insert(category)
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