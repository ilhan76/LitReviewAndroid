package com.litreview.base.ui

import android.app.Activity
import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.litreview.base.R

fun Activity.showSnack(message: String, color: Int, marginTop: Int) {
    val rootView =
        this.findViewById<View>(R.id.coordinator_layout)
    if (rootView != null) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).apply {
            view.layoutParams =
                (view.layoutParams as CoordinatorLayout.LayoutParams).apply {
                    gravity = Gravity.TOP
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