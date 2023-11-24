package com.example.kotlin.examen_mafer.framework.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.examen_mafer.data.ApiRepository
import com.example.kotlin.examen_mafer.data.network.ApiClient
import com.example.kotlin.examen_mafer.databinding.ActivityMainBinding
import com.example.kotlin.examen_mafer.framework.adapter.Adapter
import com.example.kotlin.examen_mafer.framework.viewModel.MainViewModel
import com.example.kotlin.examen_mafer.framework.viewModel.factory.MainViewModelFactory

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiRepository = ApiRepository(ApiClient())
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(apiRepository) }
    private val adapter: Adapter by lazy { Adapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        initializeObservers()
        viewModel.getCovidData("mexico") // Cambia "Mexico" por el país que desees consultar
    }

    private fun setUpRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun initializeObservers() {
        viewModel.covidData.observe(this) { apiObjects ->
            apiObjects?.let {
                adapter.setData(it)
            }
        }
    }
}