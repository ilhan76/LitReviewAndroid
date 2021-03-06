package com.litreview.f_write_review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.data.domain.Book
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.ui.SimpleTextWatcher
import com.litreview.base.ui.showErrorTopSnack
import com.litreview.base.ui.showNormalTopSnack
import com.litreview.base.util.Args
import com.litreview.f_write_review.databinding.FragmentWriteReviewBinding
import com.litreview.f_write_review.WriteReviewEvent.*
import com.litreview.i_navigation.findNavControllerSafely
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WriteReviewFragmentView :
    BaseFragment<WriteReviewState, WriteReviewEvent>(R.layout.fragment_write_review) {

    override val viewModel by viewModels<WriteReviewViewModel>()

    @Inject
    lateinit var ch: WriteReviewCommandHolder

    private val vb by viewBinding(FragmentWriteReviewBinding::bind)

    private lateinit var book: Book

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initViews()
        initListeners()
        bind()
    }

    private fun initViews() {
        with(vb.writeReviewToolbar.toolbar) {
            setNavigationIcon(com.litreview.base.R.drawable.ic_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        book = arguments?.getSerializable(Args.EXTRA_FIRST) as Book

        vb.bookNameTv.text = book.title
        vb.authorNameTv.text = book.authorName
        vb.bookRatingRb.rating = book.rate.toFloat()
        vb.bookRatingTv.text = book.rate.toString()
    }

    private fun initListeners() {
        with(vb) {
            sendReviewBtn.emitOnClick(SendReview(book = book))
            reviewTv.addTextChangedListener(SimpleTextWatcher {
                emit(ReviewTextChanged(it))
            })
            reviewRb.setOnRatingBarChangeListener { _, rate, _ ->
                emit(ReviewRateChanged(rate.toInt()))
            }
        }
    }

    private fun bind() {
        ch.closeScreen bindTo {
            findNavControllerSafely()?.popBackStack()
        }
        ch.showMessage bindTo {
            showNormalTopSnack(it)
        }
        ch.showErrorMessage bindTo {
            showErrorTopSnack(it)
        }
    }
}