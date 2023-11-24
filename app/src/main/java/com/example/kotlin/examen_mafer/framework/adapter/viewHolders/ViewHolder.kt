package com.example.kotlin.examen_mafer.framework.adapter.viewHolders

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_mafer.data.network.model.ApiDocument
import com.example.kotlin.examen_mafer.databinding.ItemCovidCaseBinding

/**
 * ViewHolder class for displaying COVID-19 data in a RecyclerView.
 *
 * @param binding The data binding object for the item view.
 */
class ViewHolder(private val binding: ItemCovidCaseBinding) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Binds the data to the view elements.
     *
     * @param entry The Map.Entry containing the date and corresponding ApiDocument.
     * @param maxCases The maximum number of cases for scaling bar widths.
     */
    fun bind(entry: Map.Entry<String, ApiDocument>, maxCases: Int) {
        val (date, data) = entry
        binding.dateTextView.text = date

        val totalCasesWidth = getBarWidth(data.total, maxCases)
        val newCasesWidth = getBarWidth(data.new, maxCases)

        val totalCasesColor = if (data.new >= 0.2 * data.total) Color.RED else Color.YELLOW
        val newCasesColor = if (data.new >= 0.2 * data.total) Color.parseColor("#FFA500") else Color.GREEN

        binding.totalCasesBar.apply {
            layoutParams.width = totalCasesWidth
            setBackgroundColor(totalCasesColor)
        }

        binding.newCasesBar.apply {
            layoutParams.width = newCasesWidth
            setBackgroundColor(newCasesColor)
        }

        binding.totalCasesTextView.text = "Total cases: ${data.total}"
        binding.newCasesTextView.text = "New cases: ${data.new}"

        binding.totalCasesBar.requestLayout()
        binding.newCasesBar.requestLayout()
    }

    /**
     * Calculates the width of a bar based on the number of cases and a maximum value.
     *
     * @param cases The number of cases to represent.
     * @param maxCases The maximum number of cases for scaling the bar width.
     * @return The width of the bar as an integer value.
     */
    private fun getBarWidth(cases: Int, maxCases: Int): Int {
        // Asumiendo que tienes un ancho máximo para las barras
        val maxWidth = 1000 // Deberías obtener esto basado en el ancho de la pantalla o del contenedor
        return if (maxCases > 0) (cases.toFloat() / maxCases * maxWidth).toInt() else 0
    }
}