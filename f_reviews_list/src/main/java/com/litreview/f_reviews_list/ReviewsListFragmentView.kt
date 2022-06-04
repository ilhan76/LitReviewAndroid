package com.litreview.f_reviews_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.data.domain.Review
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.util.Args
import com.litreview.f_reviews_list.coltrollers.ReviewItemController
import com.litreview.f_reviews_list.databinding.FragmentReviewsListBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

@AndroidEntryPoint
class ReviewsListFragmentView :
    BaseFragment<ReviewsListState, ReviewsListEvent>(R.layout.fragment_reviews_list) {

    override val viewModel by viewModels<ReviewsListViewModel>()
    private val vb by viewBinding(FragmentReviewsListBinding::bind)

    private val easyAdapter = EasyAdapter()
    private val reviewItemController = ReviewItemController()

    //todo - возможно придется переписать
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initView()
        //todo - понять, откуда бирется зацикливание при эмите
//      emit(UpdateReviewsList(arguments?.getParcelableArrayList<Review>(Args.EXTRA_FIRST) as List<Review>))
//      emit(UpdateReviewsList(emptyList()))
    }

    private fun initView() {
        with(vb.reviewsToolbar.toolbar) {
            setNavigationIcon(com.litreview.base.R.drawable.ic_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        vb.reviewsRv.adapter = easyAdapter
        val reviews = arguments?.getParcelableArrayList<Review>(Args.EXTRA_FIRST) as List<Review>
        easyAdapter.setItems(
            ItemList().addAll(reviews, reviewItemController)
        )

    }
}