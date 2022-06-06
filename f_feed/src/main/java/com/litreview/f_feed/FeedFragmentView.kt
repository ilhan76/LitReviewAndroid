package com.litreview.f_feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.data.domain.Book
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.ui.showErrorTopSnack
import com.litreview.f_feed.controllers.HeaderItemController
import com.litreview.f_feed.controllers.SearchItemController
import com.litreview.f_feed.databinding.FragmentFeedBinding
import com.litreview.f_feed.FeedEvent.*
import com.litreview.f_feed.controllers.HorizontalBooksListItemController
import com.litreview.i_navigation.findTopNavControllerSafely
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragmentView : BaseFragment<FeedState, FeedEvent>(R.layout.fragment_feed) {

    override val viewModel by viewModels<FeedViewModel>()

    @Inject
    lateinit var ch: FeedCommandHolder

    private val vb by viewBinding(FragmentFeedBinding::bind)

    private val feedAdapter = EasyAdapter()

    private val headerItemController = HeaderItemController()
    private val searchItemController = SearchItemController {
        emit(SearchViewClickEvent)
    }
    private val newBooksListItemController by lazy {
        HorizontalBooksListItemController(
            onBookClickAction = { book: Book? ->
                emit(OpenBookDetails(book))
            }
        )
    }
    private val bestBooksListItemController by lazy {
        HorizontalBooksListItemController(
            onBookClickAction = { book: Book? ->
                emit(OpenBookDetails(book))
            }
        )
    }
    private val myBooksListItemController by lazy {
        HorizontalBooksListItemController(
            onBookClickAction = { book: Book? ->
                emit(OpenBookDetails(book))
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initViews()
        bind()
        observeState { render(it) }
        emit(OnCreateEvent)
    }

    private fun initViews() {
        vb.feedRv.adapter = feedAdapter
    }

    private fun bind() {
        ch.showErrorMessage.flow bindTo {
            showErrorTopSnack(
                message = it,
                marginTop = 50
            )
        }
        ch.openTopScreen.flow bindTo {
            findTopNavControllerSafely()?.open(it)
        }
    }

    private fun render(state: FeedState) {
        with(state) {
            feedAdapter.setItems(
                ItemList.create()
                    .add(
                        userInfo?.firstName ?: "Гость",
                        headerItemController
                    )
                    .add(searchItemController)
                    .addIf(
                        newBooks.isNotEmpty(),
                        getString(R.string.feed_books_title_new_books) to newBooks,
                        newBooksListItemController
                    )
                    .addIf(
                        bestBooks.isNotEmpty(),
                        getString(R.string.feed_books_title_best_books) to bestBooks,
                        bestBooksListItemController
                    )
                    .addIf(
                        myBooks.isNotEmpty(),
                        getString(R.string.feed_books_title_my_books) to myBooks,
                        myBooksListItemController
                    )
            )
        }
    }
}