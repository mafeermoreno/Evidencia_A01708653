package com.example.kotlin.examen_mafer.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen_mafer.data.network.model.ApiObject
import com.example.kotlin.examen_mafer.databinding.ItemCovidCaseBinding
import com.example.kotlin.examen_mafer.framework.adapter.viewHolders.ViewHolder

class Adapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private var data: List<ApiObject> = listOf()

    // Actualiza los datos del adapter
    fun setData(newData: List<ApiObject>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCovidCaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Aquí necesitas decidir cómo manejarás la lista de casos, ya que ApiObject contiene un mapa.
        // Por ejemplo, podrías querer mostrar un resumen o seleccionar un día específico.
        val apiObject = data[position]
        // Supongamos que solo queremos mostrar el último día reportado.
        val lastEntry = apiObject.cases.entries.lastOrNull()
        lastEntry?.let {
            holder.bind(it, context)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}