package com.example.kotlin.examen_mafer.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examen_mafer.data.ApiRepository
import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import kotlinx.coroutines.launch

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    // LiveData para exponer los datos de COVID
    private val _covidData = MutableLiveData<List<ApiObject>>()
    val covidData: LiveData<List<ApiObject>> = _covidData

    // LiveData para exponer el máximo número de casos
    private val _maxCases = MutableLiveData<Int>()
    val maxCases: LiveData<Int> = _maxCases

    fun getCovidData(country: String) {
        viewModelScope.launch {
            val data = apiRepository.getCovidData(country) ?: emptyList()
            _covidData.postValue(data)
            // Calcular el máximo número de casos aquí y publicarlo
            _maxCases.postValue(calculateMaxCases(data))
        }
    }

    // Función auxiliar para calcular el máximo número de casos
    private fun calculateMaxCases(data: List<ApiObject>): Int {
        return data.flatMap { it.cases.values }.maxOf { it.total }
    }
}