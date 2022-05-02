package com.litreview.f_book_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.f_book_detail.databinding.FragmentBookDetailBinding

class BookDetailFragmentView : Fragment(R.layout.fragment_book_detail) {

    private val vb by viewBinding<FragmentBookDetailBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() = with(vb.detailToolbar.toolbar) {
        inflateMenu(com.litreview.base.R.menu.favorite_menu)
        setNavigationOnClickListener { requireActivity().onBackPressed() }
    }
}