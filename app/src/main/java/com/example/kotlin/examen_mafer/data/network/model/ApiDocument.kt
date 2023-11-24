package com.example.kotlin.examen_mafer.data.network.model

/**
 * Class representing an API document with information about the total and new items.
 *
 * @property total The total number of covid cases.
 * @property new The number of new cases of covid.
 */
data class ApiDocument (
    val total: Int,
    val new: Int
)