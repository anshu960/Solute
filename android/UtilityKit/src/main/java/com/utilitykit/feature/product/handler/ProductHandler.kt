package com.utilitykit.feature.product.handler

import com.google.gson.Gson
import com.utilitykit.Constants.Key
import com.utilitykit.socket.SocketEvent
import com.utilitykit.UtilityActivity
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.model.ProductStock
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.feature.product.viewModel.ProductViewModel
import com.utilitykit.feature.sync.SyncHandler
import com.utilitykit.socket.SocketService
import io.socket.emitter.Emitter
import org.json.JSONObject

class ProductHandler {

    var viewModel: ProductViewModel? = null
    val repository = ProductRepository()
    var activity : UtilityActivity? = null
    var onCreateProductCallBack : ((product: Product?) -> Unit)? = null
    var onUpdateExistingProductCallBack : ((product: Product?) -> Unit)? = null
    var onUpdateProductImageCallBack : ((product: Product?) -> Unit)? = null
    var onDeleteProductCallBack : ((product: Product?) -> Unit)? = null
    var onUpdateProductCallBack : ((product: ProductStock?) -> Unit)? = null
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
        viewModel = model
    }

     val retriveProduct = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONArray(Key.payload)
                for (i in 0 until payload.length())
                {
                    val item = payload.getJSONObject(i)
                    val product = gson.fromJson(item.toString(),Product::class.java)
                    viewModel?.insertProduct(product)
                }
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
                    onCreateProductCallBack?.let { it1 -> it1(product) }
                    viewModel?.fetchAllProduct()
                }else{
                    onCreateProductCallBack?.let { it1 -> it1(null) }
                }
            }else{
                onCreateProductCallBack?.let { it1 -> it1(null) }
            }
        }
    }

    val onUpdateProductImage = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    val product = gson.fromJson(payload.toString(),Product::class.java)
                    repository.newProductLiveData.postValue(product)
                    onUpdateProductImageCallBack?.let { it1 -> it1(product) }
                    viewModel?.fetchAllProduct()
                }else{
                    onUpdateProductImageCallBack?.let { it1 -> it1(null) }
                }
            }else{
                onUpdateProductImageCallBack?.let { it1 -> it1(null) }
            }
        }
    }

    val onUpdateProduct = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    val product = gson.fromJson(payload.toString(),Product::class.java)
                    repository.newProductLiveData.postValue(product)
                    onUpdateExistingProductCallBack?.let { it1 -> it1(product) }
                    viewModel?.fetchAllProduct()
                }else{
                    onUpdateExistingProductCallBack?.let { it1 -> it1(null) }
                }
            }else{
                onUpdateExistingProductCallBack?.let { it1 -> it1(null) }
            }
        }
    }

    val onDeleteProduct = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    val product = gson.fromJson(payload.toString(),Product::class.java)
                    repository.newProductLiveData.postValue(null)
                    onDeleteProductCallBack?.let { it1 -> it1(product) }
                    viewModel?.fetchAllProduct()
                }else{
                    onDeleteProductCallBack?.let { it1 -> it1(null) }
                }
            }else{
                onDeleteProductCallBack?.let { it1 -> it1(null) }
            }
        }
    }

    val onProductStockUpdate = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(Key.payload)){
                val payload = anyData.getJSONObject(Key.payload)
                if(payload.has(Key._id)){
                    SyncHandler.shared().syncAllBusinessData()
                    val stock = gson.fromJson(payload.toString(),ProductStock::class.java)
                    viewModel?.insertStock(stock)
                    onUpdateProductCallBack?.let { it1 -> it1(stock) }
                }else{
                    onUpdateProductCallBack?.let { it1 -> it1(null) }
                }
            }else{
                onUpdateProductCallBack?.let { it1 -> it1(null) }
            }
        }
    }

}