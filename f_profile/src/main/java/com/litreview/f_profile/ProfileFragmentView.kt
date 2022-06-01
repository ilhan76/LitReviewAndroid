package com.litreview.f_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import com.litreview.f_profile.ProfileFragmentEvent.*
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.i_navigation.findTopNavControllerSafely
import com.litreview.i_navigation.open
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragmentView :
    BaseFragment<ProfileFragmentState, ProfileFragmentEvent>(R.layout.fragment_profile) {

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
                getString(com.litreview.base.R.string.pattern_name, it.firstName, it.secondName)
            vb.profileDescription.text = it.description
            vb.profileRatingBar.rating = it.rate?.toFloat() ?: 0f
            vb.profileRatingTv.text = it.rate.toString()

            Glide.with(requireActivity())
                .load(it.avatarUrl ?: R.drawable.avatar_stub)
                .into(vb.profileAvatar)
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
        ch.openTopScreen.flow bindTo {
            findTopNavControllerSafely()?.open(it)
        }
    }
}