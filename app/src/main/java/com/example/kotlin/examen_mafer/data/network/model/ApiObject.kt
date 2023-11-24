package com.example.kotlin.examen_mafer.data.network.model

/**
 * Data class representing an API object that contains information about a country, region, and cases.
 *
 * @property country The name of the country.
 * @property region The region within the country.
 * @property cases A map containing information about cases, where the keys represent case types
 *               (e.g., "total", "new") and the values are [ApiDocument] objects describing the cases.
 */
data class ApiObject(
    val country: String,
    val region: String,
    val cases: Map<String, ApiDocument>
)