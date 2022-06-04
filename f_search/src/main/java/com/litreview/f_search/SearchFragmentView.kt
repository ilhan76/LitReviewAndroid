package com.litreview.f_search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.f_search.databinding.FragmentSearchBinding
import com.litreview.f_search.SearchEvent.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragmentView : BaseFragment<SearchState, SearchEvent>(R.layout.fragment_search) {

    override val viewModel by viewModels<SearchViewModel>()

    private val vb by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initListeners()
    }

    private fun initListeners() {
        vb.searchSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                emit(UpdateSearchText(p0.orEmpty()))
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                emit(UpdateSearchText(p0.orEmpty()))
                return true
            }
        })
        vb.searchSv.setOnSearchClickListener { emit(StartSearch) }
    }
}