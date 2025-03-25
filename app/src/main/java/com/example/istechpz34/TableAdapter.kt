package com.example.istechpz34

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TableAdapter(
    private val data: List<Pair<String, List<Double>>>,
    private val headers: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ROW = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ROW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.table_header, parent, false))
        } else {
            RowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.table_row, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bind(headers)
        } else if (holder is RowViewHolder) {
            val (name, values) = data[position - 1]
            holder.bind(name, values)
        }
    }

    override fun getItemCount(): Int = data.size + 1

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val headerRow: TextView = view.findViewById(R.id.headerRow)

        fun bind(headers: List<String>) {
            headerRow.text = headers.joinToString(" | ") { it }
        }
    }

    class RowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val rowName: TextView = view.findViewById(R.id.rowName)
        private val rowData: TextView = view.findViewById(R.id.rowData)

        fun bind(name: String, values: List<Double>) {
            rowName.text = name
            rowData.text = values.joinToString(", ") { String.format("%.2f", it) }
        }
    }
}