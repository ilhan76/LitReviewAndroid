package com.litreview.f_auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.ui.SimpleTextWatcher
import com.litreview.base.validation.getErrorMessageRes
import com.litreview.base.validation.isFailure
import com.litreview.f_auth.R
import com.litreview.f_auth.register.RegisterFragmentEvent.*
import com.litreview.f_auth.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.mvi.vm.android.MviStatefulView
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragmentView : Fragment(R.layout.fragment_register),
    MviStatefulView<RegisterState, RegisterFragmentEvent> {

    override val viewModel by viewModels<RegisterFragmentViewModel>()
    private val vb by viewBinding(FragmentRegisterBinding::bind)

    @Inject
    lateinit var ch: RegisterCommandHolder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initToolbar()
        initListeners()
        observeState { render(it) }
    }

    private fun initToolbar() = with(vb.registerToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = getString(com.litreview.base.R.string.register_toolbar_text)
        setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun initListeners() {
        vb.registerTietName.addTextChangedListener(SimpleTextWatcher {
            emit(NameChangedEvent(it.trim()))
        })
        vb.registerTietSecondName.addTextChangedListener(SimpleTextWatcher {
            emit(SecondNameChangedEvent(it.trim()))
        })
        vb.registerTietEmail.addTextChangedListener(SimpleTextWatcher {
            emit(EmailChangedEvent(it.trim()))
        })
        vb.tietPassword.addTextChangedListener(SimpleTextWatcher {
            emit(PasswordChangedEvent(it.trim()))
        })
        vb.registerTietPhone.addTextChangedListener(SimpleTextWatcher {
            emit(PhoneChangedEvent(it.trim()))
        })
        vb.registerBtn.setOnClickListener {
            emit(RegisterBtnClickEvent)
        }
    }

    private fun render(state: RegisterState) {
        vb.registerTilName.error = if (state.nameValidationResult?.isFailure() == true) {
            getString(state.nameValidationResult.getErrorMessageRes())
        } else {
            null
        }
        vb.registerTilSecondName.error = if (state.secondNameValidationResult?.isFailure() == true) {
                getString(state.secondNameValidationResult.getErrorMessageRes())
        } else {
            null
        }
        vb.registerTilEmail.error = if (state.emailValidationResult?.isFailure() == true) {
            getString(state.emailValidationResult.getErrorMessageRes())
        } else {
            null
        }
        vb.tilPassword.error = if (state.passwordValidationResult?.isFailure() == true) {
            getString(state.passwordValidationResult.getErrorMessageRes())
        } else {
             null
        }
        vb.registerTilPhone.error = if (state.phoneValidationResult?.isFailure() == true) {
            getString(state.phoneValidationResult.getErrorMessageRes())
        } else {
            null
        }
    }
}