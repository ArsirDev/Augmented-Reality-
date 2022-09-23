package com.example.animalaugmentedreality.utils

import android.app.Activity
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.WindowManager

@Suppress("DEPRECATION")
object FullScreenHelper {
    fun setFullScreenOnWindowFocusChanged(activity: Activity, hasFocus: Boolean) {
        if (hasFocus) {
            activity
                .window
                .decorView.systemUiVisibility = (SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}