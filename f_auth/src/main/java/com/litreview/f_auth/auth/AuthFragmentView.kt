package com.litreview.f_auth.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.base.ui.SimpleTextWatcher
import com.litreview.base.ui.showErrorSnack
import com.litreview.base.util.Args
import com.litreview.base.validation.getErrorMessageResOrNull
import com.litreview.f_auth.R
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.f_auth.databinding.FragmentAuthBinding
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragmentView : BaseFragment<AuthState, AuthFragmentEvent>(R.layout.fragment_auth) {

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
        initViews()
        observeState { render(it) }
    }

    private fun initViews() {
        vb.authTietEmail.setText(arguments?.getString(Args.EXTRA_FIRST).orEmpty())
        // fixme - убрать заглушку, когда сделаю не АЗ
        vb.authTietEmail.setText("user1@mail.com")
        vb.authTietPassword.setText("qweQWE123")
    }

    private fun initToolbar() = with(vb.authToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = getString(com.litreview.base.R.string.auth_toolbar_text)
        setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initListeners() {
        with(vb) {
            authTietEmail.addTextChangedListener(SimpleTextWatcher {
                emit(EmailChangedEvent(it))
            })
            authTietPassword.addTextChangedListener(SimpleTextWatcher {
                emit(PasswordChangedEvent(it))
            })
            authBtnLogin.emitOnClick(LoginClickedEvent)
        }
    }

    private fun bind() {
        ch.openScreen.flow bindTo {
            findNavControllerSafely()?.open(navCommand = it)
        }
        ch.showErrorMessage.flow bindTo {
            showErrorSnack(
                it,
                vb.authToolbar.toolbar.height
            )
        }
    }

    private fun render(state: AuthState) {
        vb.authTilEmail.trySetError(state.emailValidationResult?.getErrorMessageResOrNull())
        vb.authTilPassword.trySetError(state.passwordValidationResult?.getErrorMessageResOrNull())
    }
}