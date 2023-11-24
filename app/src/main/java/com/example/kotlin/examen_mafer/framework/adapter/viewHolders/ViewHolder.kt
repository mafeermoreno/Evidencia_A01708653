package com.example.kotlin.examen_mafer.framework.adapter.viewHolders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_mafer.R
import com.example.kotlin.examen_mafer.data.network.model.ApiDocument
import com.example.kotlin.examen_mafer.databinding.ItemBinding
import com.example.kotlin.examen_mafer.databinding.ItemCovidCaseBinding

class ViewHolder(private val binding: ItemCovidCaseBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(covidCase: Map.Entry<String, ApiDocument>, context: Context) {
        binding.dateTextView.text = covidCase.key

        // Suponiendo que tienes un layout con dos TextViews para total y nuevos casos
        binding.totalCasesTextView.text = context.getString(R.string.total_cases, covidCase.value.total)
        binding.newCasesTextView.text = context.getString(R.string.new_cases, covidCase.value.new)
    }
}