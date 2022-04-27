package com.litreview.f_main.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.navigation.findNavControllerSafely
import com.litreview.f_main.R
import com.litreview.f_main.databinding.FragmentMainBinding
import ru.surfstudio.mvi.vm.android.MviStatefulView

class MainFragmentView : Fragment(R.layout.fragment_main),
    MviStatefulView<MainFragmentState, MainFragmentEvent> {

    override val viewModel by viewModels<MainFragmentViewModel>()

    private val vb: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        vb.mainBtnSignIn.setOnClickListener {
            emit(
                MainFragmentEvent.SignInClicked(
                    findNavControllerSafely()
                )
            )
        }
        vb.mainBtnSignUp.setOnClickListener {
            emit(
                MainFragmentEvent.SignUpClicked(
                    findNavControllerSafely()
                )
            )
        }
    }

    fun render() = observeState { state ->
        //todo рендер лоадстейта
    }
}