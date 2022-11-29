package com.utilitykit.feature.productSubCategory.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.category
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.socket.SocketEvent
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.repository.ProductSubCategoryRepository
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import com.utilitykit.socket.SocketService
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
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            val business = BusinessHandler.shared().repository.business
            request.put(Key.userId,user._id)
            request.put(Key.businessID,business.value?.Id)
            SocketService.shared().send(SocketEvent.RETRIVE_PRODUCT_SUB_CATEGORY,request)
        }
    }

     val retriveProductSubCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
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
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                val productSubCategory = gson.fromJson(payload.toString(), ProductSubCategory::class.java)
                viewModel?.insert(productSubCategory)
                if(payload.has(Key._id)){
                    onCreateNewSubCategory?.let { it1 -> it1(productSubCategory) }
                }
            }
        }
    }

}