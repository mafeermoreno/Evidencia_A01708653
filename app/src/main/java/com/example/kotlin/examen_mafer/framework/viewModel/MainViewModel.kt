package com.example.kotlin.examen_mafer.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examen_mafer.data.ApiRepository
import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import kotlinx.coroutines.launch

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    private val _covidData = MutableLiveData<List<ApiObject>>()
    val covidData: LiveData<List<ApiObject>> = _covidData

    fun getCovidData(country: String) {
        viewModelScope.launch {
            val data = apiRepository.getCovidData(country) ?: emptyList()
            _covidData.postValue(data)
        }
    }
}