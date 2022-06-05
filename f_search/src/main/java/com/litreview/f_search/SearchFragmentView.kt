package com.litreview.f_search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.base.mvi.BaseFragment
import com.litreview.base.ui.showErrorTopSnack
import com.litreview.base.util.toPx
import com.litreview.f_search.databinding.FragmentSearchBinding
import com.litreview.f_search.SearchEvent.*
import com.litreview.i_navigation.findTopNavControllerSafely
import com.litreview.i_navigation.open
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragmentView : BaseFragment<SearchState, SearchEvent>(R.layout.fragment_search) {

    @Inject
    lateinit var ch: SearchCommandHolder

    override val viewModel by viewModels<SearchViewModel>()

    private val vb by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bindFlow()
        initListeners()
        bind()
    }

    private fun initListeners() {
        vb.searchSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                emit(StartSearch)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                emit(UpdateSearchText(p0.orEmpty()))
                return true
            }
        })
    }

    private fun bind() {
        ch.openTopScreen bindTo {
            findTopNavControllerSafely()?.open(it)
        }
        ch.showErrorMessage bindTo {
            showErrorTopSnack(
                it,
                100.toPx()
            )
        }
    }
}