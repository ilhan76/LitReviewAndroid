package com.litreview.f_books_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.controller.BookItemController
import com.litreview.base.data.domain.Book
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.util.Args
import com.litreview.f_books_list.databinding.FragmentBooksListBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

@AndroidEntryPoint
class BooksListFragmentView :
    BaseFragment<BooksListState, BooksListEvent>(R.layout.fragment_books_list) {

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
    }

    private fun initToolbar() = with(vb.booksToolbar.toolbar) {
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
        title = arguments?.getString(Args.EXTRA_FIRST)
        setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initViews() {
        val books = arguments?.getParcelableArrayList<Book>(Args.EXTRA_SECOND) as List<Book>
        vb.booksRv.adapter = easyAdapter
        easyAdapter.setItems(
            ItemList()
                .addAll(books, bookItemController)
        )
    }
}