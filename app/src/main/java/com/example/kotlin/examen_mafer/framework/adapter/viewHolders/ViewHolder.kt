package com.example.kotlin.examen_mafer.framework.adapter.viewHolders

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_mafer.R
import com.example.kotlin.examen_mafer.data.network.model.ApiDocument
import com.example.kotlin.examen_mafer.databinding.ItemCovidCaseBinding

class ViewHolder(private val binding: ItemCovidCaseBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(entry: Map.Entry<String, ApiDocument>, maxCases: Int) {
        val (date, data) = entry
        binding.dateTextView.text = date

        // Configurar el ancho de las barras
        val totalCasesWidth = getBarWidth(data.total, maxCases)
        val newCasesWidth = getBarWidth(data.new, maxCases)

        // Configurar los colores de fondo según la lógica de tu aplicación
        val totalCasesColor = if (data.new >= 0.2 * data.total) Color.RED else Color.YELLOW
        val newCasesColor = if (data.new >= 0.2 * data.total) Color.parseColor("#FFA500") else Color.GREEN

        // Aplicar las configuraciones a las barras
        binding.totalCasesBar.apply {
            layoutParams.width = totalCasesWidth
            setBackgroundColor(totalCasesColor)
        }

        binding.newCasesBar.apply {
            layoutParams.width = newCasesWidth
            setBackgroundColor(newCasesColor)
        }

        // Configurar textos
        binding.totalCasesTextView.text = "Total cases: ${data.total}"
        binding.newCasesTextView.text = "New cases: ${data.new}"

        // Forzar actualización de layout
        binding.totalCasesBar.requestLayout()
        binding.newCasesBar.requestLayout()
    }

    private fun getBarWidth(cases: Int, maxCases: Int): Int {
        // Asumiendo que tienes un ancho máximo para las barras
        val maxWidth = 1000 // Deberías obtener esto basado en el ancho de la pantalla o del contenedor
        return if (maxCases > 0) (cases.toFloat() / maxCases * maxWidth).toInt() else 0
    }
}