package com.example.animalaugmentedreality.views.welcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.R

class WelcomeAdapter(val layoutInflater: LayoutInflater): PagerAdapter() {

    private val fragList = listOf(
        R.layout.welcome_one_layout,
        R.layout.welcome_two_layout
    )

    override fun getCount(): Int {
        return fragList.size
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return layoutInflater.inflate(fragList[position], container, false).also {
            container.addView(it)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager: ViewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}