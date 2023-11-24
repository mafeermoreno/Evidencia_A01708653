package com.example.kotlin.examen_mafer.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_mafer.data.network.model.ApiDocument
import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import com.example.kotlin.examen_mafer.databinding.ItemCovidCaseBinding
import com.example.kotlin.examen_mafer.framework.adapter.viewHolders.ViewHolder

class Adapter(private val context: Context, private var maxCases: Int) : RecyclerView.Adapter<ViewHolder>() {
    private var entries: List<Map.Entry<String, ApiDocument>> = listOf()

    // Actualiza los datos del adapter
    fun setData(newData: List<ApiObject>) {
        // Asumiendo que quieres mostrar todos los días disponibles en el JSON,
        // convierte cada ApiObject en una lista de entradas y las almacena en 'entries'.
        entries = newData.flatMap { it.cases.entries.toList() }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCovidCaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position], maxCases)
    }

    fun updateMaxCases(newMaxCases: Int) {
        maxCases = newMaxCases
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}