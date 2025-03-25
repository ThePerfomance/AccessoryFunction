package com.example.istechpz34

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TableFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_table, container, false)

        // RecyclerView для таблицы данных экспертов
        val recyclerViewExperts = view.findViewById<RecyclerView>(R.id.recyclerViewExperts)
        recyclerViewExperts.layoutManager = LinearLayoutManager(requireContext())

        // Данные экспертов (8 значений для каждого терма)
        val expertData = listOf(
            listOf(
                listOf(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), // Низкий
                listOf(0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0), // Средний
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0)  // Высокий
            ),
            listOf(
                listOf(1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), // Низкий
                listOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0), // Средний
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0)  // Высокий
            ),
            listOf(
                listOf(1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0), // Низкий
                listOf(0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0), // Средний
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0)  // Высокий
            ),
            listOf(
                listOf(1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0), // Низкий
                listOf(0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0), // Средний
                listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0)  // Высокий
            ),
            listOf(
                listOf(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), // Низкий
                listOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0), // Средний
                listOf(0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0)  // Высокий
            )
        )

        // Адаптер для таблицы данных экспертов
        val adapter = ExpertTableAdapter(expertData)
        recyclerViewExperts.adapter = adapter

        return view
    }
}