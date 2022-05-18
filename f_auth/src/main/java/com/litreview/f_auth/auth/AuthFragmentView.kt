package com.litreview.f_auth.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.base.ui.SimpleTextWatcher
import com.litreview.f_auth.R
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.f_auth.databinding.FragmentAuthBinding
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.mvi.vm.android.MviStatefulView
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragmentView : Fragment(R.layout.fragment_auth),
    MviStatefulView<AuthState, AuthFragmentEvent> {

    override val viewModel by viewModels<AuthFragmentViewModel>()
    private val vb by viewBinding(FragmentAuthBinding::bind)

    @Inject
    lateinit var ch: AuthCommandHolder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initToolbar()
        initListeners()
        bind()
    }

    private fun initToolbar() = with(vb.authToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = getString(com.litreview.base.R.string.auth_toolbar_text)
        setNavigationOnClickListener {
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
            emit(LoginClickedEvent)
        }
    }

    private fun bind() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            ch.openScreen.flow.collect {
                findNavControllerSafely()?.open(navCommand = it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            ch.showEmailValidationError.flow.collect {
                vb.authTilEmail.error = getString(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            ch.showPasswordValidationError.flow.collect {
                vb.authTilPassword.error = getString(it)
            }
        }
    }
}