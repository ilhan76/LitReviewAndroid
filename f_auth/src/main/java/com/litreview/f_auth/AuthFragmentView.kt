package com.litreview.f_auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.navigation.findNavControllerSafely
import com.litreview.f_auth.databinding.FragmentAuthBinding
import ru.surfstudio.mvi.vm.android.MviStatefulView

class AuthFragmentView: Fragment(R.layout.fragment_auth), MviStatefulView<AuthState, AuthEvent> {

    override val viewModel by viewModels<AuthViewModel>()
    private val vb by viewBinding(FragmentAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        with(vb.authToolbar.toolbar) {
            title = getString(com.litreview.base.R.string.auth_title_text)
            setNavigationOnClickListener {
                emit(AuthEvent.BackPressed(findNavControllerSafely()))
            }
        }
    }
}