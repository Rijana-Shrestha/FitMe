package com.rijana.fitme.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rijana.fitme.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_home)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager_home)

        // Set up ViewPager2 adapter for Discover, Trainers, and MyPlan tabs
        viewPager.adapter = HomePagerAdapter(this)

        val tabTitles = arrayOf(
            getString(R.string.tab_title_discover),
            getString(R.string.tab_title_trainers),
            getString(R.string.tab_title_my_plan)
        )

        // Bind TabLayout and ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}