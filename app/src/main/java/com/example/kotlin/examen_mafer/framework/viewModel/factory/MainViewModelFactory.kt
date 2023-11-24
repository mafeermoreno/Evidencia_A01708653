package com.example.kotlin.examen_mafer.framework.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.examen_mafer.data.ApiRepository
import com.example.kotlin.examen_mafer.framework.viewModel.MainViewModel

/**
 * Factory class for creating instances of [MainViewModel].
 *
 * @param apiRepository The [ApiRepository] to be used by the ViewModel.
 */
class MainViewModelFactory(private val apiRepository: ApiRepository) : ViewModelProvider.Factory {
    /**
     * Creates a new instance of [MainViewModel].
     *
     * @param modelClass The class of the ViewModel to create.
     * @return A new instance of [MainViewModel].
     * @throws IllegalArgumentException if the specified [modelClass] is not [MainViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
