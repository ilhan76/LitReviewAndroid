package com.litreview.base.ui

import android.app.Activity
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.litreview.base.R

fun Fragment.showErrorBottomSnack(
    message: String,
    marginTop: Int = 0
) {
    requireActivity().showSnack(
        message,
        R.color.red_error,
        marginTop,
        Gravity.BOTTOM
    )
}

fun Fragment.showErrorTopSnack(
    message: String,
    marginTop: Int = 0
) {
    requireActivity().showSnack(
        message,
        R.color.red_error,
        marginTop,
        Gravity.TOP
    )
}

fun Fragment.showNormalTopSnack(
    message: String,
    marginTop: Int = 0
) {
    requireActivity().showSnack(
        message,
        R.color.green,
        marginTop,
        Gravity.TOP
    )
}

private fun Activity.showSnack(
    message: String,
    color: Int,
    marginTop: Int = 0,
    gravityRes: Int
) {
    val rootView =
        this.findViewById<View>(R.id.coordinator_layout)
    if (rootView != null) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).apply {
            view.layoutParams =
                (view.layoutParams as CoordinatorLayout.LayoutParams).apply {
                    gravity = gravityRes
                    setMargins(
                        leftMargin,
                        marginTop,
                        rightMargin,
                        bottomMargin
                    )
                }
        }
            .setBackgroundTint(ContextCompat.getColor(this, color))
            .show()
    }
}