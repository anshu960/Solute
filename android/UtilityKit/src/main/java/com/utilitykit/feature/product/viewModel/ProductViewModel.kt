package com.utilitykit.feature.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.Constants.Key
import com.utilitykit.SocketUtill.SocketEvent
import com.utilitykit.SocketUtill.SocketManager
import com.utilitykit.dataclass.User
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProductViewModel (private val productRepository: ProductRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }
    val allProduct : LiveData<List<Product>>
    get() = productRepository.allProduct

    val newProduct : LiveData<Product>
        get() = productRepository.newProduct

    fun createNewProduct(name:String,description:String,categoryid :String,subCategoryId:String,mrp:Float,costPrice:Float,salePrice : Float){
        val request = JSONObject()
        val user = User()
        if(BusinessHandler.shared().repository.business != null){
            val business = BusinessHandler.shared().repository.business
            request.put(Key.userId,user._id)
            request.put(Key.businessID,business!!.Id)
            request.put(Key.categoryId,categoryid)
            request.put(Key.subCategoryID,categoryid)
            request.put(Key.name,name)
            request.put(Key.description,description)
            request.put(Key.manageInventory,true)
            request.put(Key.taxIncluded,true)
            request.put(Key.SGST,0)
            request.put(Key.CGST,0)
            request.put(Key.IGST,0)
            request.put(Key.CESS,0)
            request.put(Key.VAT,0)
            request.put(Key.discount,(mrp*0.1))
            request.put(Key.MRP,mrp)
            request.put(Key.MRP,mrp)
            request.put(Key.price,(mrp*0.9))
            request.put(Key.costPrice,(mrp*0.6))
            request.put(Key.finalPrice,(mrp*0.9))
            request.put(Key.tax,0)
            SocketManager.send(SocketEvent.CREATE_PRODUCT,request)
        }
    }

}