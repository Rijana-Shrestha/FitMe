package com.rijana.fitme.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DiscoverFragment()
            1 -> TrainersFragment()
            2 -> MyPlanFragment()
            else -> DiscoverFragment()
        }
    }
}