package com.litreview.base.mvi

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseFragment(
    id: Int
): Fragment(id) {

    infix fun <T> SharedFlow<T>.bindTo(action: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            this@bindTo.collect {
                action(it)
            }
        }
    }

    fun TextInputLayout.trySetError(messageRes: Int?) {
        this.error = if (messageRes != null) {
            getString(messageRes)
        } else {
            null
        }
    }
}