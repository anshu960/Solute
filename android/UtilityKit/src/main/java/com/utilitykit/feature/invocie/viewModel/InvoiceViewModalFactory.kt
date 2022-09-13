package com.utilitykit.feature.invoice.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utilitykit.feature.invoice.repository.InvoiceRepository


class InvoiceViewModalFactory(private val invoiceRepository: InvoiceRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InvoiceViewModel(invoiceRepository) as T
    }
}