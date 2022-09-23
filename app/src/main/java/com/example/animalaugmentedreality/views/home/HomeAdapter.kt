package com.example.animalaugmentedreality.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.R
import kotlinx.android.synthetic.main.item_image_viewpager.view.*

class HomeAdapter(private val layoutInflater: LayoutInflater): PagerAdapter() {

    private val imageList = listOf(
        R.drawable.home_bear,
        R.drawable.home_bird,
        R.drawable.home_dog,
        R.drawable.home_elephant,
        R.drawable.home_laba,
        R.drawable.home_kucing,
        R.drawable.home_tiger,
        R.drawable.home_wolf
    )

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return layoutInflater.inflate(R.layout.item_image_viewpager, container, false).also {
            it.iv_image_data.setImageResource(imageList[position])
            container.addView(it)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager: ViewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

}