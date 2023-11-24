package com.example.kotlin.examen_mafer.data.network

import com.example.kotlin.examen_mafer.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object responsible for providing and configuring network-related dependencies.
 */
object NetworkModuleDI {
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient()

    /**
     * Creates an instance of [ApiService] with the specified base URL, HTTP client, and Gson converter factory.
     *
     * @return An instance of [ApiService] configured for making API requests.
     */
    operator fun invoke(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(ApiService::class.java)
    }
}