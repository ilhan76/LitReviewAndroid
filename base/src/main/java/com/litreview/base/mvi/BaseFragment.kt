package com.litreview.base.mvi

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.SharedFlow
import ru.surfstudio.mvi.core.event.Event
import ru.surfstudio.mvi.vm.android.MviStatefulView

abstract class BaseFragment<S : Any, E : Event>(
    id: Int
) : Fragment(id), MviStatefulView<S, E> {

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

    fun View.emitOnClick(event: E) = setOnClickListener { emit(event) }
}