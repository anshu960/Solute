package com.utilitykit.feature.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.Constants.Key.Companion.quantity
import com.utilitykit.database.DatabaseHandler
import com.utilitykit.socket.SocketEvent
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.AuthHandler
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.mediaFile.handler.MediaFileHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.model.ProductBarCode
import com.utilitykit.feature.product.model.ProductStock
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.socket.SocketService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {
    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    val allProduct: LiveData<ArrayList<Product>>
        get() = productRepository.allProduct

    val newProduct: LiveData<Product>
        get() = productRepository.newProduct

    val selectedProduct: LiveData<Product>
        get() = productRepository.selectedProduct

    fun loadProduct() {
        BusinessHandler.shared().activity?.runOnUiThread {
            productRepository.productLiveData.postValue(arrayListOf())
            if (!BusinessHandler.shared().repository.business.value?.Id.isNullOrEmpty()) {
                val businessId = BusinessHandler.shared().repository.business.value?.Id
                DatabaseHandler.shared().database.productDao().getProductsFor(businessId!!)
                    .observe(BusinessHandler.shared().activity) {
                        if (!it.isNullOrEmpty()) {
                            productRepository.productLiveData.postValue(it as ArrayList<Product>)
                        }
                    }
            }
        }
    }

    fun loadProductBarCode(product: Product) {
        productRepository.productBarCodeLiveData.postValue(arrayListOf())
        scope.launch {
         val fetchedBarCodes = DatabaseHandler.shared().database.productBarCodeDao().getForProduct(product.Id)
            if(fetchedBarCodes.isNotEmpty()){
                productRepository.productBarCodeLiveData.postValue(fetchedBarCodes as ArrayList<ProductBarCode>)
            }
        }
    }

    fun findBarcodeById(barcode:String,callBack :(ProductBarCode?)->Unit){
        scope.launch {
            val fetchedBarCode = DatabaseHandler.shared().database.productBarCodeDao().findById(barcode)
            callBack(fetchedBarCode)
        }
    }

    fun findProductByBarCode(barcode:String,callBack :(Product?)->Unit){
        scope.launch {
            findBarcodeById(barcode){barcode->
                if(barcode != null && !barcode!!.ProductID.isNullOrEmpty()){
                    scope.launch {
                        val foundProduct = DatabaseHandler.shared().database.productDao().findById(barcode!!.ProductID)
                        callBack(foundProduct)
                    }
                }
            }
        }
    }

    fun fetchAllProduct() {
        val request = JSONObject()
        val user = User()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.userId, user._id)
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        SocketService.shared().send(SocketEvent.RETRIVE_PRODUCT, request)
    }

    fun createNewProduct(request: JSONObject) {
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT, request)
    }

    fun updateProduct(request: JSONObject) {
        SocketService.shared().send(SocketEvent.UPDATE_PRODUCT, request)
    }

    fun updateProductImage(product: Product, image: String) {
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.fileURL, image)
        request.put(Key._id, product.Id)
        request.put(Key.userId, user._id)
        request.put(Key.deviceId, AuthHandler.shared().deviceId)
        request.put(Key.featureObjectID, product.Id)
        MediaFileHandler.shared().viewModel?.createNew(request)
    }


    fun deleteProduct(product: Product) {
        val user = User()
        val request = JSONObject()
        if (BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.businessID, business.value?.Id)
        }
        request.put(Key.userId, user._id)
        request.put(Key._id, product.Id)
        SocketService.shared().send(SocketEvent.DELETE_PRODUCT, request)
    }

    fun removeStockQuantity(product: Product, quantity: Int, message: String) {
        val user = User()
        val request = JSONObject()
        if (BusinessHandler.shared().repository.business != null) {
            val business = BusinessHandler.shared().repository.business
            request.put(Key.businessID, business.value?.Id)
        }
        request.put(Key.productID, product.Id)
        request.put(Key.userId, user._id)
        request.put(Key.quantity, quantity)
        request.put(Key.comment, message)
        SocketService.shared().send(SocketEvent.REMOVE_STOCK_QUANTITY, request)
    }


    fun addStockQuantity(product: Product, quantity: Int, message: String) {
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.productID, product.Id)
        request.put(Key.userId, user._id)
        request.put(Key.quantity, quantity)
        request.put(Key.comment, message)
        SocketService.shared().send(SocketEvent.ADD_STOCK_QUANTITY, request)
    }

    fun resetStockQuantity(product: Product, quantity: Int, message: String) {
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.productID, product.Id)
        request.put(Key.userId, user._id)
        request.put(Key.quantity, quantity)
        request.put(Key.comment, message)
        SocketService.shared().send(SocketEvent.RESET_STOCK_QUANTITY, request)
    }

    fun insertStock(stockEntry: ProductStock) {
        viewModelScope.launch {
            DatabaseHandler.shared().database.productStockDao()
                .insert(stockEntry)
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            DatabaseHandler.shared().database.productDao()
                .insert(product)
        }
    }

    fun createBarCode(product: Product, code: String) {
        val unixTime = System.currentTimeMillis()
        val user = User()
        val request = JSONObject()
        val business = BusinessHandler.shared().repository.business
        request.put(Key.uniqueId, unixTime)
        request.put(Key.businessID, business.value?.Id)
        request.put(Key.productID, product.Id)
        request.put(Key.userId, user._id)
        request.put(Key.barcode, code)
        SocketService.shared().send(SocketEvent.CREATE_PRODUCT_BAR_CODE, request)
    }

    fun insertProductBarCode(barCode: ProductBarCode) {
        viewModelScope.launch {
            DatabaseHandler.shared().database.productBarCodeDao()
                .insert(barCode)
        }
    }


}