package com.litreview.f_start.main_screen

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.f_start.main_screen.MainFragmentEvent.*
import com.litreview.f_start.R
import com.litreview.f_start.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.mvi.vm.android.MviStatefulView

@AndroidEntryPoint
class MainFragmentView : Fragment(R.layout.fragment_main),
    MviStatefulView<MainFragmentState, MainFragmentEvent> {

    override val viewModel by viewModels<MainFragmentViewModel>()

    private val vb: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        vb.mainBanner.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.image_onboarding))
        initListeners()
    }

    private fun initListeners() {
        vb.mainBtnSignIn.setOnClickListener {
            emit(SignInClicked(findNavControllerSafely()))
        }
        vb.mainBtnSignUp.setOnClickListener {
            emit(SignUpClicked(findNavControllerSafely()))
        }
    }

    fun render() = observeState { state ->
        //todo рендер лоадстейта
    }
}