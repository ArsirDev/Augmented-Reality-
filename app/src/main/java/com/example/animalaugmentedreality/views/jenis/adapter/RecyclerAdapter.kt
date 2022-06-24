package com.example.animalaugmentedreality.views.jenis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.databinding.ItemListAnimalLayoutBinding

class RecyclerAdapter(val dataList: List<Int>): PagerAdapter() {

    override fun getPageWidth(position: Int): Float {
        return 0.4f
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return ItemListAnimalLayoutBinding.inflate(LayoutInflater.from(container.context), container, false).also { binding ->
            with(binding) {
                ivImageKarnivora.setImageResource(dataList[position])
                ivImageKarnivora.setOnClickListener {
                    onItemClickListener?.let {
                        it(position)
                    }
                }
                container.addView(root)
            }
        }.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager: ViewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
}