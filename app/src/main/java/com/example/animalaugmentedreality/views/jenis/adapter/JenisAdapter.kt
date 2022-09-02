package com.example.animalaugmentedreality.views.jenis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.databinding.ImageJenisItemBinding
import com.example.animalaugmentedreality.utils.setOnClickListenerWithDebounce

class JenisAdapter: PagerAdapter() {

    private val imageList = listOf(
        R.drawable.home_bear,
        R.drawable.home_bird,
        R.drawable.home_dog,
        R.drawable.home_elephant,
    )

    private val titleList = listOf(
        "Karnivora",
        "Herbivora",
        "Omnivora",
        "Insektivora",
    )

    private val descripList = listOf(
        "Karnivora merupakan pemakan daging. Hewan yang masukkedalam golongan karnivor karena memangsa hewan lain, hewan ini juga disebut sebagai predator. Predator mendapatkan mangsanya dengan memburu mangsanya itu",
        "Herbivora adalah hewan yang makanannya berupa tumbuhan seperti rumput, daun-daunan, biji-bijian dan buah-buahan. Contoh hewan yang tergolong herbivora juga sangat mudah kita temui dalam kehidupan sehari-hari seperti sapi, kerbau, kambing, kuda, rusa, dan domba.",
        "Omnivora adalah hewan pemakan segala atau yang sering disebut sarwaboga berasal dari bahasa latin, yakni “omni” yang berarti “semua” dan “vorare” yang berarti “melahap”. Dengan demikian, omnivora merupakan hewan yang memakan tumbuh-tumbuhan dan hewan sebagai sumber makanan pokok.",
        "Insektivora adalah sebutan untuk organisme yang makanannya adalah serangga dan hewan kecil lainnya. Binatang yang dimaksud sebetulnya bukan binatang besar tetapi serangga seperti lalat, semut, laba-laba, hingga anak kodok. Sedangkan tanaman yang dimaksud adalah Kantong semar (Nephantes sp)."
    )

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return ImageJenisItemBinding.inflate(LayoutInflater.from(container.context), container, false).also { bind ->
            with(bind) {
                ivRoundImage.setImageResource(imageList[position])
                tvTitle.text = titleList[position]
                tvDescription.text = descripList[position]
                cvItem.setOnClickListenerWithDebounce {
                    onItemClickListener?.let {
                        it(position)
                    }
                }
                container.addView(bind.root)
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