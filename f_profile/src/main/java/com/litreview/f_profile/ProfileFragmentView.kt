package com.litreview.f_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.f_profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.mvi.vm.android.MviStatefulView
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragmentView : Fragment(R.layout.fragment_profile),
    MviStatefulView<ProfileFragmentState, ProfileFragmentEvent> {

    override val viewModel by viewModels<ProfileFragmentViewModel>()
    private val vb by viewBinding(FragmentProfileBinding::bind)
    @Inject
    lateinit var ch: ProfileFragmentCommandHolder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
    }
}