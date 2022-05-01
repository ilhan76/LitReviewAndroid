package com.litreview.f_auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.litreview.f_auth.R
import ru.surfstudio.mvi.vm.android.MviStatefulView

class RegisterFragmentView: Fragment(R.layout.fragment_register), MviStatefulView<AuthState, RegisterEvent> {

    override val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}