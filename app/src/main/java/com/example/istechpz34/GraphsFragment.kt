package com.example.istechpz34

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class GraphsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_graphs, container, false)

        // График 1: Функции принадлежности
        val chart1 = view.findViewById<LineChart>(R.id.lineChart1)
        setupMembershipFunctions(chart1)

        // График 2: Субнормальное и нормализованное множества
        val chart2 = view.findViewById<LineChart>(R.id.lineChart2)
        setupFuzzyAndNormalizedSets(chart2)

        return view
    }

    private fun setupMembershipFunctions(chart: LineChart) {
        // Интервалы роста (8 интервалов)
        val heightIntervals = listOf(160, 165, 170, 175, 180, 185, 190, 195)

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

        // Усреднение значений для каждого интервала
        val lowMembership = mutableListOf<Double>()
        val mediumMembership = mutableListOf<Double>()
        val highMembership = mutableListOf<Double>()

        for (interval in 0 until heightIntervals.size) {
            var lowSum = 0.0
            var mediumSum = 0.0
            var highSum = 0.0

            for (expert in expertData) {
                lowSum += expert[0][interval]
                mediumSum += expert[1][interval]
                highSum += expert[2][interval]
            }

            lowMembership.add(lowSum / expertData.size)
            mediumMembership.add(mediumSum / expertData.size)
            highMembership.add(highSum / expertData.size)
        }

        // Подготовка данных для графика
        val lowEntries = mutableListOf<Entry>()
        val mediumEntries = mutableListOf<Entry>()
        val highEntries = mutableListOf<Entry>()

        for (i in heightIntervals.indices) {
            lowEntries.add(Entry(heightIntervals[i].toFloat(), lowMembership[i].toFloat()))
            mediumEntries.add(Entry(heightIntervals[i].toFloat(), mediumMembership[i].toFloat()))
            highEntries.add(Entry(heightIntervals[i].toFloat(), highMembership[i].toFloat()))
        }

        // Создание наборов данных
        val lowDataSet = LineDataSet(lowEntries, "Низкий").apply {
            color = Color.RED
            setCircleColor(Color.RED)
            lineWidth = 2f
            circleRadius = 4f
            valueTextColor = Color.RED
        }

        val mediumDataSet = LineDataSet(mediumEntries, "Средний").apply {
            color = Color.GREEN
            setCircleColor(Color.GREEN)
            lineWidth = 2f
            circleRadius = 4f
            valueTextColor = Color.GREEN
        }

        val highDataSet = LineDataSet(highEntries, "Высокий").apply {
            color = Color.BLUE
            setCircleColor(Color.BLUE)
            lineWidth = 2f
            circleRadius = 4f
            valueTextColor = Color.BLUE
        }

        // Настройка графика
        val lineData = LineData(lowDataSet, mediumDataSet, highDataSet)
        chart.data = lineData
        chart.description.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.animateX(1000)
        chart.invalidate()
    }

    private fun setupFuzzyAndNormalizedSets(chart: LineChart) {
        // Данные для парного сравнения
        val pairwiseComparison = listOf(
            listOf(1.0, 6.0 / 4.0, 8.0 / 4.0, 6.0 / 4.0, 1.0),
            listOf(4.0 / 6.0, 1.0, 4.0 / 6.0, 1.0, 4.0 / 6.0),
            listOf(4.0 / 8.0, 6.0 / 8.0, 1.0, 6.0 / 8.0, 4.0 / 8.0),
            listOf(4.0 / 6.0, 1.0, 8.0 / 6.0, 1.0, 4.0 / 6.0),
            listOf(1.0, 6.0 / 4.0, 8.0 / 4.0, 6.0 / 4.0, 1.0)
        )

        // Нормализация исходных данных парного сравнения
        val normalizedPairwiseComparison = normalizePairwiseComparison(pairwiseComparison)

        // Субнормальное множество: средние значения для каждой строки
        val fuzzyEntries = mutableListOf<Entry>()
        for (i in normalizedPairwiseComparison.indices) {
            val averageValue = normalizedPairwiseComparison[i].average().toFloat()
            fuzzyEntries.add(Entry(i.toFloat(), averageValue))
        }

        // Нормализованное множество: нормализованные средние значения
        val maxValue = fuzzyEntries.maxByOrNull { it.y }?.y ?: 1.0f
        val normalizedEntries = mutableListOf<Entry>()
        for (entry in fuzzyEntries) {
            val normalizedValue = entry.y / maxValue
            normalizedEntries.add(Entry(entry.x, normalizedValue))
        }

        // Создание наборов данных
        val fuzzyDataSet = LineDataSet(fuzzyEntries, "Субнормальное множество").apply {
            color = Color.MAGENTA
            setCircleColor(Color.MAGENTA)
            lineWidth = 2f
            circleRadius = 4f
            valueTextColor = Color.MAGENTA
        }

        val normalizedDataSet = LineDataSet(normalizedEntries, "Нормализованное множество").apply {
            color = Color.CYAN
            setCircleColor(Color.CYAN)
            lineWidth = 2f
            circleRadius = 4f
            valueTextColor = Color.CYAN
        }

        // Настройка графика
        val lineData = LineData(fuzzyDataSet, normalizedDataSet)
        chart.data = lineData
        chart.description.text = "Субнормальное и нормализованное множества"
        chart.description.textColor = Color.BLACK
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.animateX(1000)
        chart.invalidate()
    }

    private fun normalizePairwiseComparison(data: List<List<Double>>): List<List<Double>> {
        // Нахождение максимального значения во всей матрице
        val maxVal = data.flatten().maxOrNull() ?: 1.0

        // Нормализация каждого значения
        return data.map { row ->
            row.map { value -> value / maxVal }
        }
    }
}