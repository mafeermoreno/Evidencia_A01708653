package com.example.kotlin.examen_mafer.domain

import com.example.kotlin.examen_mafer.data.ApiRepository
import com.example.kotlin.examen_mafer.data.network.model.ApiObject

/**
 * Class representing a list requirement for retrieving COVID-19 data.
 *
 * @param apiRepository An instance of [ApiRepository] used for accessing COVID-19 data.
 */
class ListRequirement(private val apiRepository: ApiRepository) {
    /**
     * Invokes the list requirement to retrieve COVID-19 data for a specific country.
     *
     * @param country The name of the country for which data is requested.
     * @return A list of [ApiObject] containing COVID-19 data for the specified country,
     *         or null in case of an exception.
     */
    suspend operator fun invoke(country: String): List<ApiObject>? {
        return apiRepository.getCovidData(country)
    }
}