package com.litreview.f_auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.base.ui.SimpleTextWatcher
import com.litreview.f_auth.AuthFragmentEvent.*
import com.litreview.f_auth.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.mvi.vm.android.MviStatefulView

@AndroidEntryPoint
class AuthFragmentView : Fragment(R.layout.fragment_auth), MviStatefulView<AuthState, AuthFragmentEvent> {

    override val viewModel by viewModels<AuthFragmentViewModel>()
    private val vb by viewBinding(FragmentAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initToolbar()
        initListeners()
    }

    private fun initToolbar() = with(vb.authToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = getString(com.litreview.base.R.string.auth_toolbar_text)
        setNavigationOnClickListener {
//            emit(BackPressed(findNavControllerSafely()))
            requireActivity().onBackPressed()
        }
    }

    private fun initListeners() {
        vb.authTietEmail.addTextChangedListener(SimpleTextWatcher {
            emit(EmailChangedEvent(it))
        })
        vb.authTietPassword.addTextChangedListener(SimpleTextWatcher {
            emit(PasswordChangedEvent(it))
        })
        vb.authBtnLogin.setOnClickListener {
            emit(LoginClickedEvent(findNavControllerSafely()))
        }
    }
}