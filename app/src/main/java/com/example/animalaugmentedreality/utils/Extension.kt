package com.example.animalaugmentedreality.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.animalaugmentedreality.utils.Content.MIN_OPENGL_VERSION
import com.google.ar.sceneform.ux.ArFragment

fun scaleAnimationCustom(
    fromX: Float,
    toX: Float,
    fromY: Float,
    toY: Float,
    animation_one: Int,
    privotXValue: Float,
    animation_two: Int,
    privotYValue: Float
): ScaleAnimation {
    return ScaleAnimation(
        fromX,
        toX,
        fromY,
        toY,
        animation_one,
        privotXValue,
        animation_two,
        privotYValue
    )
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.removeView() {
    this.visibility = View.GONE
}

fun P_E_M(tag: String, msg: String){
    Log.e(tag, msg)
}

fun View.setOnClickListenerWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun checkIsSupportDeviceOrFinish(activity: Activity): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Log.e("$activity", "Sceneform requires Android N or later")
        Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG)
            .show()
        activity.finish()
        return false
    }
    val openGlVersionString =
        (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
            .deviceConfigurationInfo
            .glEsVersion
    if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
        P_E_M("$activity", "Sceneform requires OpenGL ES 3.0 later")
        Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
            .show()
        activity.finish()
        return false
    }
    return true
}



