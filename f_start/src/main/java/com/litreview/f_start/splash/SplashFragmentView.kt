package com.litreview.f_start.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_start.R
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragmentView : BaseFragment<SplashState, SplashEvent>(R.layout.fragment_splash) {

    override val viewModel by viewModels<SplashViewModel>()

    @Inject
    lateinit var ch: SplashCommandHolder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        bind()
        emit(SplashEvent.TryToLoadProfile)
    }

    private fun bind() {
        ch.openScreen.flow bindTo {
            findNavControllerSafely()?.open(it)
        }
    }
}