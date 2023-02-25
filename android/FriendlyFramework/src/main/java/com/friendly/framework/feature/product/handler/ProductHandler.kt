package com.friendly.framework.feature.product.handler

import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.model.ProductBarCode
import com.friendly.framework.feature.product.model.ProductStock
import com.friendly.framework.feature.product.repository.ProductRepository
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.friendly.framework.feature.sync.SyncHandler
import com.google.gson.Gson
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
    var onCreateProductBarCodeCallBack : ((product: ProductBarCode?) -> Unit)? = null
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONArray(KeyConstant.payload)
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                if(payload.has(KeyConstant._id)){
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                if(payload.has(KeyConstant._id)){
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                if(payload.has(KeyConstant._id)){
                    val product = gson.fromJson(payload.toString(),Product::class.java)
                    viewModel?.insertProduct(product)
                    onUpdateExistingProductCallBack?.let { it1 -> it1(product) }
                    viewModel?.loadProduct()
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                if(payload.has(KeyConstant._id)){
                    val product = gson.fromJson(payload.toString(),Product::class.java)
                    viewModel?.insertProduct(product)
                    onDeleteProductCallBack?.let { it1 -> it1(product) }
                    viewModel?.loadProduct()
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
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                if(payload.has(KeyConstant._id)){
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

    val onCreateProductBarCode = Emitter.Listener {
        if (it.isNotEmpty())
        {
            val anyData = it.first() as JSONObject
            if (anyData.has(KeyConstant.payload)){
                val payload = anyData.getJSONObject(KeyConstant.payload)
                if(payload.has(KeyConstant._id)){
                    SyncHandler.shared().syncAllBusinessData()
                    val data = gson.fromJson(payload.toString(),ProductBarCode::class.java)
                    viewModel?.insertProductBarCode(data)
                    onCreateProductBarCodeCallBack?.let { it1 -> it1(data) }
                }else{
                    onCreateProductBarCodeCallBack?.let { it1 -> it1(null) }
                }
            }else{
                onCreateProductBarCodeCallBack?.let { it1 -> it1(null) }
            }
        }
    }

}