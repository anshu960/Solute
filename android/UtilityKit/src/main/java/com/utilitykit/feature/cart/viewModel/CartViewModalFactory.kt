package com.utilitykit.feature.cart.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.cart.repository.CartRepository

class CartViewModalFactory(private val cartRepository: CartRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartRepository) as T
    }
}