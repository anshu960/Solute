package com.utilitykit.feature.cart.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.product.repository.CartRepository
import com.utilitykit.feature.product.repository.ProductRepository
import com.utilitykit.feature.product.viewModel.ProductViewModel

class CartViewModalFactory(private val cartRepository: CartRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartRepository) as T
    }
}