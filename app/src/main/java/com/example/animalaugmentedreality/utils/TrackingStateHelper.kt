package com.example.animalaugmentedreality.utils

import android.app.Activity
import android.view.WindowManager
import com.google.ar.core.TrackingState

class TrackingStateHelper {
    private val INSUFFICIENT_FEATURES_MESSAGE =
        "Can't find anything. Aim device at a surface with more texture or color."
    private val EXCESSIVE_MOTION_MESSAGE = "Moving too fast. Slow down."
    private val INSUFFICIENT_LIGHT_MESSAGE = "Too dark. Try moving to a well-lit area."
    private val INSUFFICIENT_LIGHT_ANDROID_S_MESSAGE = ("Too dark. Try moving to a well-lit area."
            + " Also, make sure the Block Camera is set to off in system settings.")
    private val BAD_STATE_MESSAGE =
        "Tracking lost due to bad internal state. Please try restarting the AR experience."
    private val CAMERA_UNAVAILABLE_MESSAGE =
        "Another app is using the camera. Tap on this app or try closing the other one."
    private val ANDROID_S_SDK_VERSION = 31

    private var activity: Activity? = null

    private var previousTrackingState: TrackingState? = null

    fun TrackingStateHelper(activity: Activity?) {
        this.activity = activity
    }

    fun updateKeepScreenOnFlag(trackingState: TrackingState) {
        if (trackingState == previousTrackingState) {
            return
        }

        previousTrackingState = trackingState
        when (trackingState) {
            TrackingState.PAUSED -> {
                return
            }
            TrackingState.STOPPED -> {
                activity!!.runOnUiThread {
                    activity!!.window
                        .clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            }
            TrackingState.TRACKING -> {
                activity!!.runOnUiThread {
                    activity!!.window
                        .addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            }
        }
    }
}