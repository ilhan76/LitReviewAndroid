package com.litreview.f_auth

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.surfstudio.mvi.vm.android.MviStatefulView

class AuthFragmentView: Fragment(R.layout.fragment_auth), MviStatefulView<AuthState, AuthEvent> {

    override val viewModel by viewModels<AuthViewModel>()
}