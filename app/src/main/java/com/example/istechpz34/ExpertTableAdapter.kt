package com.example.istechpz34

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpertTableAdapter(private val data: List<List<List<Double>>>) :
    RecyclerView.Adapter<ExpertTableAdapter.ExpertViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_expert_row, parent, false)
        return ExpertViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpertViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class ExpertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val expertName: TextView = itemView.findViewById(R.id.expertName)
        private val lowValues: TextView = itemView.findViewById(R.id.lowValues)
        private val mediumValues: TextView = itemView.findViewById(R.id.mediumValues)
        private val highValues: TextView = itemView.findViewById(R.id.highValues)

        fun bind(data: List<List<Double>>) {
            expertName.text = "Эксперт ${adapterPosition + 1}"
            lowValues.text = data[0].joinToString(", ")
            mediumValues.text = data[1].joinToString(", ")
            highValues.text = data[2].joinToString(", ")
        }
    }
}