package com.example.kotlin.examen_mafer.data.network

import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Interface representing an API service for retrieving COVID-19 data.
 */
interface ApiService {
    /**
     * Retrieves COVID-19 data for a specific country.
     *
     * @param token The API key to authenticate the request.
     * @param country The name of the country for which data is requested.
     * @return A list of [ApiObject] containing COVID-19 data for the specified country.
     */
    @GET("covid19")
    suspend fun getCovidData(
        @Header("X-Api-Key") token: String,
        @Query("country") country: String
    ): List<ApiObject>
}