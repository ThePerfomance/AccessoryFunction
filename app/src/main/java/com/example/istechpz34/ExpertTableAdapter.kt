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

        // Низкий
        private val low_160: TextView = itemView.findViewById(R.id.low_160)
        private val low_165: TextView = itemView.findViewById(R.id.low_165)
        private val low_170: TextView = itemView.findViewById(R.id.low_170)
        private val low_175: TextView = itemView.findViewById(R.id.low_175)
        private val low_180: TextView = itemView.findViewById(R.id.low_180)
        private val low_185: TextView = itemView.findViewById(R.id.low_185)
        private val low_190: TextView = itemView.findViewById(R.id.low_190)
        private val low_195: TextView = itemView.findViewById(R.id.low_195)

        // Средний
        private val medium_160: TextView = itemView.findViewById(R.id.medium_160)
        private val medium_165: TextView = itemView.findViewById(R.id.medium_165)
        private val medium_170: TextView = itemView.findViewById(R.id.medium_170)
        private val medium_175: TextView = itemView.findViewById(R.id.medium_175)
        private val medium_180: TextView = itemView.findViewById(R.id.medium_180)
        private val medium_185: TextView = itemView.findViewById(R.id.medium_185)
        private val medium_190: TextView = itemView.findViewById(R.id.medium_190)
        private val medium_195: TextView = itemView.findViewById(R.id.medium_195)

        // Высокий
        private val high_160: TextView = itemView.findViewById(R.id.high_160)
        private val high_165: TextView = itemView.findViewById(R.id.high_165)
        private val high_170: TextView = itemView.findViewById(R.id.high_170)
        private val high_175: TextView = itemView.findViewById(R.id.high_175)
        private val high_180: TextView = itemView.findViewById(R.id.high_180)
        private val high_185: TextView = itemView.findViewById(R.id.high_185)
        private val high_190: TextView = itemView.findViewById(R.id.high_190)
        private val high_195: TextView = itemView.findViewById(R.id.high_195)

        fun bind(expertData: List<List<Double>>) {
            // Наименование эксперта
            expertName.text = "Эксперт ${adapterPosition + 1}"

            // Заполняем значения для интервалов
            setValues(expertData[0], low_160, low_165, low_170, low_175, low_180, low_185, low_190, low_195)
            setValues(expertData[1], medium_160, medium_165, medium_170, medium_175, medium_180, medium_185, medium_190, medium_195)
            setValues(expertData[2], high_160, high_165, high_170, high_175, high_180, high_185, high_190, high_195)
        }

        private fun setValues(values: List<Double>, vararg textViews: TextView) {
            for ((index, textView) in textViews.withIndex()) {
                textView.text = values.getOrNull(index)?.let { "%.0f".format(it) } ?: "0"
            }
        }
    }
}