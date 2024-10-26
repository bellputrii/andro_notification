package com.bell.mycourse

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SelectionsPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = QuizFragment()
            2 -> fragment = MateriFragment()
        }
        return fragment as Fragment
    }
    override fun getItemCount(): Int {
        return 3
    }

}