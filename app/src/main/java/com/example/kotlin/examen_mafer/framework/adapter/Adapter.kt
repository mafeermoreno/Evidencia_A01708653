package com.example.kotlin.examen_mafer.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_mafer.data.network.model.ApiDocument
import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import com.example.kotlin.examen_mafer.databinding.ItemCovidCaseBinding
import com.example.kotlin.examen_mafer.framework.adapter.viewHolders.ViewHolder

/**
 * RecyclerView adapter for displaying COVID-19 data.
 *
 * @param context The application context.
 * @param maxCases The maximum number of cases for scaling bar widths.
 */
class Adapter(private val context: Context, private var maxCases: Int) : RecyclerView.Adapter<ViewHolder>() {
    private var entries: List<Map.Entry<String, ApiDocument>> = listOf()

    /**
     * Updates the data of the adapter.
     *
     * @param newData The new list of [ApiObject] containing COVID-19 data.
     */
    fun setData(newData: List<ApiObject>) {
        // Asumiendo que quieres mostrar todos los días disponibles en el JSON,
        // convierte cada ApiObject en una lista de entradas y las almacena en 'entries'.
        entries = newData.flatMap { it.cases.entries.toList() }
        notifyDataSetChanged()
    }

    /**
     * Called by RecyclerView when it needs a new [ViewHolder] to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The type of the new View.
     * @return A new instance of [ViewHolder] that holds the inflated view for an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCovidCaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the data item in the dataset.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position], maxCases)
    }

    /**
     * Updates the maximum number of cases for scaling bar widths.
     *
     * @param newMaxCases The new maximum number of cases.
     */
    fun updateMaxCases(newMaxCases: Int) {
        maxCases = newMaxCases
        notifyDataSetChanged()
    }

    /**
     * Gets the total number of items to be displayed in the RecyclerView.
     *
     * @return The number of items to be displayed.
     */
    override fun getItemCount(): Int {
        return entries.size
    }
}