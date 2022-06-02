package com.litreview.f_reviews_list

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_reviews_list.databinding.FragmentReviewsListBinding

class ReviewsListFragmentView :
    BaseFragment<ReviewsListFragmentState, ReviewsListFragmentEvent>(R.layout.fragment_reviews_list) {

    override val viewModel by viewModels<ReviewsListFragmentViewModel>()
    private val vb by viewBinding(FragmentReviewsListBinding::bind)
}