package com.friendly.framework.feature.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.database.DatabaseHandler
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.model.Product
import com.friendly.framework.feature.product.model.ProductBarCode
import com.friendly.framework.feature.product.model.ProductStock
import com.friendly.framework.feature.product.network.ProductNetwork
import com.friendly.framework.feature.product.repository.ProductRepository
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.friendly.frameworkt.feature.business.handler.AuthHandler
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    val allProduct: LiveData<ArrayList<Product>>
        get() = productRepository.allProduct

    val newProduct: LiveData<Product>
        get() = productRepository.newProduct

    val selectedProduct: LiveData<Product>
        get() = productRepository.selectedProduct

    fun loadProduct() {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            productRepository.productLiveData.postValue(arrayListOf())
            if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
                val businessId = BusinessHandler.shared().repository.business.value?.Id
                if (!businessId.isNullOrEmpty()) {
                    productRepository.productLiveData.postValue(
                        DatabaseHandler.shared().database.productDao()
                            .getProductsFor(businessId) as? ArrayList<Product>
                    )
                }
            }
        }
    }

    fun loadProductBarCode(product: Product) {
        productRepository.productBarCodeLiveData.postValue(arrayListOf())
        scope.launch {
            val fetchedBarCodes =
                DatabaseHandler.shared().database.productBarCodeDao().getForProduct(product.Id)
            if (fetchedBarCodes.isNotEmpty()) {
                productRepository.productBarCodeLiveData.postValue(fetchedBarCodes as ArrayList<ProductBarCode>)
            }
        }
    }

    fun findBarcodeById(barcode: String, callBack: (ProductBarCode?) -> Unit) {
        scope.launch {
            val fetchedBarCode =
                DatabaseHandler.shared().database.productBarCodeDao().findById(barcode)
            callBack(fetchedBarCode)
        }
    }

    fun findProductByBarCode(barcode: String, callBack: (Product?) -> Unit) {
        scope.launch {
            findBarcodeById(barcode) { barcode ->
                if (barcode != null && !barcode!!.ProductID.isNullOrEmpty()) {
                    scope.launch {
                        val foundProduct = DatabaseHandler.shared().database.productDao()
                            .findById(barcode.ProductID)
                        callBack(foundProduct)
                    }
                }
            }
        }
    }

    fun fetchAllProduct() {
        val request = JSONObject()
        val user = FriendlyUser()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        ProductNetwork.shared().retrieve(request) { responseJson ->
            try {
                val payload = responseJson?.getJSONArray(KeyConstant.payload)
                if (payload != null) {
                    for (i in 0 until payload!!.length()) {
                        val item = payload.getJSONObject(i)
                        val product = Gson().fromJson(item.toString(), Product::class.java)
                        insertProduct(product)
                    }
                }
                loadProduct()
            } catch (e: Exception) {
                e.printStackTrace()

            }

        }
    }

    fun createNewProduct(request: JSONObject) {
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT, request)
    }

    fun updateProduct(request: JSONObject) {
        SocketService.shared().send(SocketEvent.UPDATE_PRODUCT, request)
    }

    fun updateProductImage(product: Product, image: String) {
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.fileURL, image)
        request.put(KeyConstant._id, product.Id)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.deviceId, AuthHandler.shared().deviceId)
        request.put(KeyConstant.featureObjectID, product.Id)
        MediaFileHandler.shared().viewModel?.createNew(request)
    }

    fun deleteProduct(product: Product) {
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant._id, product.Id)
        product.IsDeleted = true
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.productDao()
                .update(product)
        }
        SocketService.shared().send(SocketEvent.DELETE_PRODUCT, request)
    }

    fun removeStockQuantity(product: Product, quantity: Int, message: String) {
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.productID, product.Id)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.quantity, quantity)
        request.put(KeyConstant.comment, message)
        SocketService.shared().send(SocketEvent.REMOVE_STOCK_QUANTITY, request)
    }

    fun addStockQuantity(product: Product, quantity: Int, message: String) {
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.productID, product.Id)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.quantity, quantity)
        request.put(KeyConstant.comment, message)
        SocketService.shared().send(SocketEvent.ADD_STOCK_QUANTITY, request)
    }

    fun resetStockQuantity(product: Product, quantity: Int, message: String) {
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.productID, product.Id)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.quantity, quantity)
        request.put(KeyConstant.comment, message)
        SocketService.shared().send(SocketEvent.RESET_STOCK_QUANTITY, request)
    }

    fun insertStock(stockEntry: ProductStock) {
        scope.launch {
            DatabaseHandler.shared().database.productStockDao()
                .insert(stockEntry)
        }
    }

    fun insertProduct(product: Product) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.productDao()
                .insert(product)
        }
    }

    fun createBarCode(product: Product, code: String) {
        val unixTime = System.currentTimeMillis()
        val user = FriendlyUser()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(KeyConstant.uniqueId, unixTime)
        request.put(KeyConstant.businessID, business.value?.Id)
        request.put(KeyConstant.productID, product.Id)
        request.put(KeyConstant.userId, user._id)
        request.put(KeyConstant.barcode, code)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_BAR_CODE, request)
    }

    fun insertProductBarCode(barCode: ProductBarCode) {
        CoroutineScope(Job() + Dispatchers.IO).launch {
            DatabaseHandler.shared().database.productBarCodeDao()
                .insert(barCode)
        }
    }


}
