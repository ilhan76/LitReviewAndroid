package com.litreview.f_books_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.controller.BookItemController
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.util.Args
import com.litreview.f_books_list.BooksListEvent.*
import com.litreview.f_books_list.databinding.FragmentBooksListBinding
import com.litreview.i_navigation.findTopNavControllerSafely
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

@AndroidEntryPoint
class BooksListFragmentView :
    BaseFragment<BooksListState, BooksListEvent>(R.layout.fragment_books_list) {

    @Inject
    lateinit var ch: BooksListCommandHolder

    override val viewModel by viewModels<BooksListViewModel>()
    private val vb by viewBinding(FragmentBooksListBinding::bind)

    private val easyAdapter = EasyAdapter()
    private val bookItemController = BookItemController {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initToolbar()
        initViews()
        bind()
        observeState { render(it) }
        emit(GetBooksFromBuffer)
    }

    private fun initToolbar() = with(vb.booksToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = arguments?.getString(Args.EXTRA_FIRST)
        setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initViews() {
        vb.booksRv.adapter = easyAdapter
    }

    private fun bind() {
        ch.openTopScreen bindTo {
            findTopNavControllerSafely()?.open(it)
        }
    }

    private fun render(state: BooksListState) {
        easyAdapter.setItems(
            ItemList()
                .addAll(state.books, bookItemController)
        )
    }
}
