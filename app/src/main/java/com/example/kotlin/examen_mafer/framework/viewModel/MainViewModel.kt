package com.example.kotlin.examen_mafer.framework.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examen_mafer.data.ApiRepository
import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing COVID-19 data.
 *
 * @param apiRepository The [ApiRepository] for fetching COVID-19 data.
 */
class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    // LiveData to expose COVID-19 data
    private val _covidData = MutableLiveData<List<ApiObject>>()
    val covidData: LiveData<List<ApiObject>> = _covidData

    // LiveData to expose the maximum number of cases
    private val _maxCases = MutableLiveData<Int>()
    val maxCases: LiveData<Int> = _maxCases

    /**
     * Fetches COVID-19 data for the specified country and updates LiveData.
     *
     * @param country The name of the country for which COVID-19 data is requested.
     */
    fun getCovidData(country: String) {
        viewModelScope.launch {
            val data = apiRepository.getCovidData(country) ?: emptyList()
            _covidData.postValue(data)
            // Calcular el máximo número de casos aquí y publicarlo
            _maxCases.postValue(calculateMaxCases(data))
        }
    }

    /**
     * Calculates the maximum number of cases from the provided COVID-19 data.
     *
     * @param data The list of [ApiObject] containing COVID-19 data.
     * @return The maximum number of cases.
     */
    private fun calculateMaxCases(data: List<ApiObject>): Int {
        return data.flatMap { it.cases.values }.maxOf { it.total }
    }
}