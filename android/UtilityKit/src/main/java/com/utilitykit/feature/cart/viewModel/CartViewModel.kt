package com.utilitykit.feature.cart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.repository.CartRepository
import com.utilitykit.feature.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class CartViewModel (private val cartRepository: CartRepository):ViewModel(){
    init {
        viewModelScope.launch (Dispatchers.IO){

        }
    }

    val cartProducts : LiveData<List<Product>>
        get() = cartRepository.cartProducts

    val cart : LiveData<JSONObject>
        get() = cartRepository.cart

    val cartCount : LiveData<Int>
    get()=cartRepository.cartCount

    val discount : LiveData<Int>
        get() = cartRepository.discount

    val subtotal : LiveData<Int>
        get() = cartRepository.subtotal

    val tax : LiveData<Int>
        get() = cartRepository.tax

    val instantDiscount : LiveData<Int>
        get() = cartRepository.instantDiscount

    val totalAmount : LiveData<Int>
        get() = cartRepository.totalAmount


    fun updateProductsInCart(){
        var productsAddedToCart : ArrayList<Product> = arrayListOf()
        ProductHandler.shared().repository.allProduct.value?.forEach {
            if(cart.value?.has(it.Id) == true){
                productsAddedToCart.add(it)
            }
        }
        cartRepository.cartProductLiveData.postValue(productsAddedToCart)
        updatePricesInCart()
    }

    fun updateInstantDiscount(amount : Int){
        cartRepository?.instantDiscountLiveData?.postValue(amount)
    }

    fun updatePricesInCart(){
        var priceDiscount = 0
        var priceSubtotal = 0
        var priceTax = 0
        var priceTotal = 0
        var instantDiscountPrice = 0
        cartProducts.value?.forEach {
            val quanity = getProductQuantity(it)
            if(it.Discount != null){
                priceDiscount = priceDiscount + (it.Discount!! * quanity)
            }
            if(it.Price != null){
                priceSubtotal = priceSubtotal + (it.Price!! * quanity)
            }
            if(it.Tax != null){
                priceTax = priceTax + (it.Tax!! * quanity)
            }
        }
        if(instantDiscount.value != null){
            instantDiscountPrice = instantDiscount.value!!
        }
        priceTotal = (priceSubtotal + priceTax)-instantDiscountPrice
        cartRepository.subtotalLiveData.postValue(priceSubtotal)
        cartRepository.discountLiveData.postValue(priceDiscount)
        cartRepository.taxlLiveData.postValue(priceTax)
        cartRepository.totalAmountLiveData.postValue(priceTotal)
    }

    fun updateCount(){
        var count = 0
        cart.value?.keys()?.forEach {
            count+=1
        }
        cartRepository.cartCountLiveData.postValue(count)
    }
    fun getProductQuantity(product: Product):Int{
        var quanity = 0
        if(cart.value != null && cart.value!!.has(product.Id)){
            quanity = cart.value!!.getInt(product.Id!!)
        }
        return quanity
    }

    fun resetCart(){
        cartRepository.cartProductLiveData.postValue(arrayListOf())
        cartRepository.cartLiveData.postValue(JSONObject())
        cartRepository.cartCountLiveData.postValue(0)
        cartRepository.discountLiveData.postValue(0)
        cartRepository.subtotalLiveData.postValue(0)
        cartRepository.taxlLiveData.postValue(0)
        cartRepository.instantDiscountLiveData.postValue(0)
        cartRepository.totalAmountLiveData.postValue(0)
    }
}