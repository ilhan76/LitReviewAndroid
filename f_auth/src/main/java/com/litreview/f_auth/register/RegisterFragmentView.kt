package com.litreview.f_auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import com.litreview.base.ui.SimpleTextWatcher
import com.litreview.base.ui.showErrorSnack
import com.litreview.base.validation.getErrorMessageResOrNull
import com.litreview.f_auth.R
import com.litreview.f_auth.register.RegisterFragmentEvent.*
import com.litreview.f_auth.databinding.FragmentRegisterBinding
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.i_navigation.open
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
        bind()
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

    private fun bind() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            ch.openScreen.flow.collect {
                findNavControllerSafely()?.open(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            ch.showErrorMessage.flow.collect {
                showErrorSnack(
                    message = it,
                    marginTop = vb.registerToolbar.toolbar.height
                )
            }
        }
    }

    private fun render(state: RegisterState) {
        vb.registerTilName.trySetError(state.nameValidationResult?.getErrorMessageResOrNull())
        vb.registerTilSecondName.trySetError(state.secondNameValidationResult?.getErrorMessageResOrNull())
        vb.registerTilEmail.trySetError(state.emailValidationResult?.getErrorMessageResOrNull())
        vb.tilPassword.trySetError(state.passwordValidationResult?.getErrorMessageResOrNull())
        vb.registerTilPhone.trySetError(state.phoneValidationResult?.getErrorMessageResOrNull())
    }

    private fun TextInputLayout.trySetError(messageRes: Int?) {
        this.error = if (messageRes != null) {
            getString(messageRes)
        } else {
            null
        }
    }
}