package com.litreview.f_book_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.litreview.base.data.domain.Book
import com.litreview.base.util.Args
import com.litreview.f_book_detail.databinding.FragmentBookDetailBinding

class BookDetailFragmentView : Fragment(R.layout.fragment_book_detail) {

    private val vb by viewBinding<FragmentBookDetailBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
    }

    private fun initToolbar() = with(vb.detailToolbar.toolbar) {
        inflateMenu(com.litreview.base.R.menu.favorite_menu)
        setNavigationOnClickListener { requireActivity().onBackPressed() }
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
    }

    private fun initViews() {
        val book = arguments?.getSerializable(Args.EXTRA_FIRST) as Book

        with(vb) {
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
            detailAboutAuthor.text = book.author?.description
            detailDescription.text = book.description
        }
    }
}