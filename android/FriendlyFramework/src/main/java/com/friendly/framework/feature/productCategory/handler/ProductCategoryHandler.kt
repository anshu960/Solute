package com.friendly.framework.feature.productCategory.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productCategory.repository.ProductCategoryRepository
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModel
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
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
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.RETRIVE_PRODUCT_CATEGORY,request)
    }

     val retriveProductCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val category = gson.fromJson(payload.toString(),ProductCategory::class.java)
                productCategoryViewModel?.insert(category)
                onCreateNewCategory?.let { it1 -> it1(category) }
            }
        }
    }

}