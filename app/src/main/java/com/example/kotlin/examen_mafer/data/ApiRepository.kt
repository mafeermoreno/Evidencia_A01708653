package com.example.kotlin.examen_mafer.data

import com.example.kotlin.examen_mafer.data.network.ApiClient
import com.example.kotlin.examen_mafer.data.network.model.ApiObject

/**
 * Repository class for fetching COVID-19 data from an API.
 *
 * @param apiService An instance of [ApiService] used for making API requests.
 */
class ApiRepository(private val apiClient: ApiClient) {
    /**
     * Retrieves COVID-19 data for a specific country.
     *
     * @param country The name of the country for which data is requested.
     * @return A list of [ApiObject] containing COVID-19 data for the specified country,
     *         or null in case of an exception.
     */
    suspend fun getCovidData(country: String): List<ApiObject>? {
        return apiClient.getCovidData(country)
    }
}