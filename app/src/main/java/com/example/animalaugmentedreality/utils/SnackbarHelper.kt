package com.example.animalaugmentedreality.utils

import android.R
import android.app.Activity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
import com.google.android.material.snackbar.Snackbar

class SnackbarHelper {
    private val BACKGROUND_COLOR = -0x40cdcdce
    private var messageSnackbar: Snackbar? = null

    private enum class DismissBehavior {
        HIDE, SHOW, FINISH
    }

    private val maxLines = 2
    private var lastMessage = ""
    private val snackbarView: View? = null

    fun isShowing(): Boolean {
        return messageSnackbar != null
    }

    fun showMessage(activity: Activity?, message: String) {
        if (!message.isEmpty() && (!isShowing() || lastMessage != message)) {
            lastMessage = message
            show(
                activity!!,
                message,
                DismissBehavior.HIDE
            )
        }
    }

    fun showError(activity: Activity?, errorMessage: String?) {
        show(
            activity!!,
            errorMessage.toString(),
            DismissBehavior.FINISH
        )
    }

    private fun show(
        activity: Activity,
        message: String,
        dismissBehavior: DismissBehavior
    ) {
        activity.runOnUiThread {
            messageSnackbar = Snackbar.make(
                snackbarView ?: activity.findViewById(R.id.content),
                message,
                Snackbar.LENGTH_INDEFINITE
            )
            messageSnackbar!!.view
                .setBackgroundColor(BACKGROUND_COLOR)
            if (dismissBehavior != DismissBehavior.HIDE) {
                messageSnackbar!!.setAction(
                    "Dismiss",
                    View.OnClickListener { messageSnackbar!!.dismiss() })
                if (dismissBehavior == DismissBehavior.FINISH) {
                    messageSnackbar!!.addCallback(
                        object : BaseCallback<Snackbar?>() {
                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                super.onDismissed(transientBottomBar, event)
                                activity.finish()
                            }
                        })
                }
            }
            (messageSnackbar!!
                .view
                .findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView).maxLines =
                maxLines
            messageSnackbar!!.show()
        }
    }
}