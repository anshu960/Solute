package com.friendly.framework.feature.productSubCategory.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.friendly.framework.feature.productSubCategory.repository.ProductSubCategoryRepository
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.gson.Gson
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductSubCategoryHandler{

    var viewModel: ProductSubCategoryViewModel? = null
    val repository = ProductSubCategoryRepository()
    var onCreateNewSubCategory : ((category: ProductSubCategory)->Unit)? = null
    var onSelectSubCategory : ((category: ProductSubCategory)->Unit)? = null
    var activity = UtilityActivity()
    val gson = Gson()
    init {
        instance = this
    }
    companion object{
        private var instance: ProductSubCategoryHandler? = null
        fun shared() : ProductSubCategoryHandler {
            if(instance != null){
                return instance as ProductSubCategoryHandler
            }else{
                return ProductSubCategoryHandler()
            }
        }
    }

    fun setup(model: ProductSubCategoryViewModel){
        viewModel = model
    }


    fun fetchAllProductSubCategory(){
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId,user._id)
        request.put(KeyConstant.businessID,business.value?.Id)
        SocketService.shared().send(SocketEvent.RETRIVE_PRODUCT_SUB_CATEGORY,request)
    }

     val retriveProductSubCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val productSubCategory = gson.fromJson(item.toString(), ProductSubCategory::class.java)
                    viewModel?.insert(productSubCategory)
                }
            }
        }
    }

    val onCreateProductSubCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                val productSubCategory = gson.fromJson(payload.toString(), ProductSubCategory::class.java)
                viewModel?.insert(productSubCategory)
                viewModel?.loadSubCategory()
                if(payload.has(KeyConstant._id)){
                    onCreateNewSubCategory?.let { it1 -> it1(productSubCategory) }
                }
            }
        }
    }

}