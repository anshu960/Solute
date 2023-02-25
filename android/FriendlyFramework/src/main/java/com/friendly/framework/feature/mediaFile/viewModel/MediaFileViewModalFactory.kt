package com.friendly.framework.feature.mediaFile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.feature.mediaFile.repository.MediaFileRepository

class  MediaFileViewModalFactory(private val repository: MediaFileRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  MediaFileViewModel(repository) as T
    }
}