package com.utilitykit.feature.productSubCategory.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.repository.ProductSubCategoryRepository
import com.utilitykit.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductSubCategoryHandler{

    var productSubCategoryViewModel: ProductSubCategoryViewModel? = null
    val repository = ProductSubCategoryRepository()
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
        productSubCategoryViewModel = model
    }


    fun fetchAllProductSubCategory(){
        val request = JSONObject()
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            val business = BusinessHandler.shared().repository.business
            request.put(Key.userId,user._id)
            request.put(Key.businessID,business!!.Id)
            SocketManager.send(SocketEvent.RETRIVE_PRODUCT_SUB_CATEGORY,request)
        }
    }

     val retriveProductSubCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                var allProductSubCategory : ArrayList<ProductSubCategory> = arrayListOf()
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val product = gson.fromJson(item.toString(), ProductSubCategory::class.java)
                    allProductSubCategory.add(product)
                }
                repository.subCategoryLiveData.postValue(allProductSubCategory)
            }
        }
    }

    val onCreateProductSubCategory = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    activity.runOnUiThread {
                        activity.toast("Sub Category Created Successfully")
                        activity.onBackPressed()
                    }
                }
            }
        }
    }

}