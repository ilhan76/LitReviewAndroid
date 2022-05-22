package com.litreview.f_auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.f_auth.R
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
        initToolbar()
    }

    private fun initToolbar() = with(vb.registerToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = getString(com.litreview.base.R.string.register_toolbar_text)
        setNavigationOnClickListener { requireActivity().onBackPressed() }
    }
}