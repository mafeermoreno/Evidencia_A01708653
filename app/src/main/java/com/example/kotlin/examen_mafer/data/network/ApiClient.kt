package com.example.kotlin.examen_mafer.data.network

import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import com.example.kotlin.examen_mafer.utils.Constants

/**
 * This class represents an API client for retrieving COVID-19 data.
 *
 * @property api An instance of [ApiService] used for making API requests.
 */
class ApiClient {
    private val api: ApiService = NetworkModuleDI()

    /**
     * Retrieves COVID-19 data for a specific country.
     *
     * @param country The name of the country for which data is requested.
     * @return A list of [ApiObject] containing COVID-19 data for the specified country,
     *         or null in case of an exception.
     */
    suspend fun getCovidData(country: String): List<ApiObject>? {
        return try {
            api.getCovidData(token = Constants.TOKEN, country = country)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}