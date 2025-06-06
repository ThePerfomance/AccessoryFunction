package com.example.istechpz34

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GraphsFragment() // Вкладка с графиками
            1 -> TableFragment()  // Вкладка с таблицей данных
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}