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
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(ApiRepository(ApiClient())) }
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        viewModel.maxCases.observe(this) { maxCases ->
            if (adapter == null) {
                adapter = Adapter(this, maxCases)
                binding.recyclerview.adapter = adapter
            } else {
                adapter?.updateMaxCases(maxCases)
            }
        }

        viewModel.covidData.observe(this) { apiObjects ->
            apiObjects?.let { data ->
                if (adapter == null) {
                    // Asume que maxCases ya está establecido, puedes manejar este caso mejor dependiendo de tu lógica
                    adapter = Adapter(this, viewModel.maxCases.value ?: 0)
                    binding.recyclerview.adapter = adapter
                }
                adapter?.setData(data)
            }
        }

        viewModel.getCovidData("Mexico")
    }

    private fun setUpRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        // El adapter se establecerá una vez que los datos estén disponibles
    }
}
