package com.example.animalaugmentedreality.utils

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.animalaugmentedreality.R
import com.example.animalaugmentedreality.utils.Content.MIN_ALPHA
import com.example.animalaugmentedreality.utils.Content.MIN_SCALE
import kotlin.math.abs
import kotlin.math.max


class ZoomOutPageTransformer: ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 1
                    } else {
                        horzMargin + vertMargin / 1
                    }
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = (MIN_ALPHA + (((scaleFactor - MIN_SCALE) / (2 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}

class transformer: ViewPager.PageTransformer {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun transformPage(page: View, position: Float) {
        val pageMarginPx = page.resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = page.resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val screenWidth = page.resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth
        page.apply {
            when {
                position < -1 -> {
                    P_E_M("zoom", "nol: $position")
                    alpha = 0f
                }

                position <= 1 -> {
                    P_E_M("zoom", "satu: $position")
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = 1f
                }

                else -> {
                    alpha = 0f
                }
            }

//            when {
//                position < -1 -> {
//                    translationY = position * abs(offsetPx)
//                }
//
//                position <= 1 -> {
//                    translationY = position * -offsetPx
//                }
//            }
//
//            translationY = position * abs(offsetPx).apply {
//                if (position == 1f) {
//                    translationY = position * -offsetPx
//                } else if(position == 2f) {
//                    translationX = position * -offsetPx
//                }
//            }

            translationY = position * abs(offsetPx)
        }
    }
}