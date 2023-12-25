package com.example.travel_app_uas

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException

class TabAdapter (activity : AppCompatActivity) : FragmentStateAdapter(activity){
    val page = arrayOf<Fragment>(RegisterFragment(), LoginFragment())
    override fun getItemCount(): Int {
        return page.size
    }

    override fun createFragment(position: Int): Fragment {
        return page[position]
    }

    //   override fun createFragment(position: Int): Fragment {
    //       return when (position) {
    //           0 -> HomeFragment()
    //           1 -> MaterialFragment()
    //           2 -> QuizFragment()
    //           else -> throw IllegalArgumentException("Position out of array")
    //       }
    //   }
}