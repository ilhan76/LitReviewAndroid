package com.litreview.f_feed

import androidx.fragment.app.viewModels
import com.litreview.base.mvi.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedView : BaseFragment<FeedState, FeedEvent>(R.layout.fragment_feed) {

    override val viewModel by viewModels<FeedViewModel>()
}