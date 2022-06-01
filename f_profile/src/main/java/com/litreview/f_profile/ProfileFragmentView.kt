package com.litreview.f_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.f_profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.mvi.vm.android.MviStatefulView
import com.litreview.f_profile.ProfileFragmentEvent.*
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.i_navigation.open
import kotlinx.coroutines.flow.SharedFlow
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
        initToolbar()
        initListeners()
        bind()
        observeState { render(it) }
        emit(ViewCreatedEvent)
    }

    private fun render(state: ProfileFragmentState) {
        state.userInfo?.let {
            vb.profileName.text =
                getString(com.litreview.base.R.string.pattern_name, it.name, it.secondName)
        }
    }

    private fun initToolbar() = with(vb.profileToolbar.toolbar) {
        title = getString(com.litreview.base.R.string.profile_toolbar_title)
    }

    private fun initListeners() {
        vb.profileCvMyReview.emitOnClick(OpenMyReviewEvent)
        vb.profileCvMyBooks.emitOnClick(OpenMyBooksEvent)
        vb.profileBtnLogout.emitOnClick(LogoutEvent)
    }

    private fun bind() {
        ch.openScreen.flow bindTo {
            findNavControllerSafely()?.open(it)
        }
    }

    private fun View.emitOnClick(event: ProfileFragmentEvent) = setOnClickListener { emit(event) }

    //todo вынести в базовый фрагмент
    private infix fun <T> SharedFlow<T>.bindTo(action: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            this@bindTo.collect {
                action(it)
            }
        }
    }

}