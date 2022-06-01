package com.litreview.f_books_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_books_list.databinding.FragmentBooksListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksListFragmentView :
    BaseFragment<BooksListFragmentState, BooksListFragmentEvent>(R.layout.fragment_books_list) {

    override val viewModel by viewModels<BooksListFragmentViewModel>()
    private val vb by viewBinding(FragmentBooksListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
    }
}