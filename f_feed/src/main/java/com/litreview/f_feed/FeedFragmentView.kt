package com.litreview.f_feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_feed.controllers.HeaderItemController
import com.litreview.f_feed.controllers.SearchItemController
import com.litreview.f_feed.databinding.FragmentFeedBinding
import com.litreview.f_feed.FeedEvent.*
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

@AndroidEntryPoint
class FeedFragmentView : BaseFragment<FeedState, FeedEvent>(R.layout.fragment_feed) {

    override val viewModel by viewModels<FeedViewModel>()

    private val vb by viewBinding(FragmentFeedBinding::bind)

    private val feedAdapter = EasyAdapter()

    private val headerItemController = HeaderItemController()
    private val searchItemController = SearchItemController {
        emit(SearchViewClickEvent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initViews()
        observeState { render(it) }
    }

    private fun initViews() {
        vb.feedRv.adapter = feedAdapter
        feedAdapter.setItems(
            ItemList.create()
                .add(headerItemController)
                .add(searchItemController)
        )
    }

    private fun render(state: FeedState) {

    }
}