package com.litreview.f_book_detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.litreview.base.data.domain.Book
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.ui.showErrorTopSnack
import com.litreview.base.util.Args
import com.litreview.f_book_detail.databinding.FragmentBookDetailBinding
import com.litreview.f_book_detail.BookDetailEvent.*
import com.litreview.i_navigation.findNavControllerSafely
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookDetailFragmentView :
    BaseFragment<BookDetailState, BookDetailEvent>(R.layout.fragment_book_detail) {

    override val viewModel by viewModels<BookDetailViewModel>()

    @Inject
    lateinit var ch: BookDetailCommandHolder

    private val vb by viewBinding<FragmentBookDetailBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initViews()
        initListeners()
        bind()
        observeState { render(it) }
        emit(OnCreateEvent)
        emit(UpdateBookEvent(arguments?.getSerializable(Args.EXTRA_FIRST) as Book))
    }

    private fun initViews() {
        with(vb.detailToolbar.toolbar) {
            setNavigationOnClickListener { requireActivity().onBackPressed() }
            setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        }
    }

    private fun initListeners() {
        vb.bookmarkBtn.emitOnClick(BookmarkClickEvent)
        vb.detailReadReviewBtn.emitOnClick(OpenReviewsScreen)
        vb.detailWriteReviewBtn.emitOnClick(OpenWriteReviewScreen)
    }

    private fun bind() {
        ch.showFailAddToBookmarksMessage bindTo {
            showErrorTopSnack(
                message = getString(R.string.fail_to_add_to_bookmarks_error_text),
                marginTop = vb.detailToolbar.toolbar.height
            )
        }
        ch.openScreen bindTo {
            findNavControllerSafely()?.open(it)
        }
    }

    private fun render(state: BookDetailState) {
        with(vb) {
            state.book?.let { book ->
                Glide.with(requireActivity())
                    .load(
                        book.imageUrl.takeIf {
                            it.isNotEmpty()
                        } ?: com.litreview.base.R.drawable.book_cover
                    ).into(vb.detailBookPoster)

                bookName.text = book.title
                itemBookAuthor.text = getString(
                    com.litreview.base.R.string.pattern_author_name,
                    book.author?.firstName,
                    book.author?.middleName,
                    book.author?.lastName
                )
                ratingBar.rating = book.rate.toFloat()
                ratingTv.text = book.rate.toString()
                detailAboutAuthor.text = book.author?.description
                detailDescription.text = book.description

                detailWriteReviewBtn.isVisible = state.isAuthorized
                bookmarkBtn.isVisible = state.isAuthorized
                bookmarkBtn.setImageDrawable(
                    if (state.isAdded) {
                        AppCompatResources.getDrawable(
                            requireContext(),
                            com.litreview.base.R.drawable.ic_bookmark_filled
                        )
                    } else {
                        AppCompatResources.getDrawable(
                            requireContext(),
                            com.litreview.base.R.drawable.ic_bookmark
                        )
                    }
                )
            }

        }
    }
}