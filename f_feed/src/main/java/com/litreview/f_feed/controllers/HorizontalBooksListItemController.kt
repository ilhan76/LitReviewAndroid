package com.litreview.f_feed.controllers

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.litreview.base.controller.BookItemController
import com.litreview.base.data.domain.Book
import com.litreview.base.databinding.ItemBookBinding
import com.litreview.f_feed.R
import com.litreview.f_feed.databinding.ItemHorizontalBooksListBinding
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class HorizontalBooksListItemController(
    private val onBookClickAction: (Book?) -> Unit
) : BindableItemController<Pair<String, List<Book>>, HorizontalBooksListItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: Pair<String, List<Book>>) = data.hashCode()

    inner class Holder(
        parent: ViewGroup
    ) : BindableViewHolder<Pair<String, List<Book>>>(parent, R.layout.item_horizontal_books_list) {

        private val binding = ItemHorizontalBooksListBinding.bind(itemView)
        private val easyAdapter = EasyAdapter()
        private val bookItemController = BookItemController(onBookClickAction)

        init {
            binding.booksRv.adapter = easyAdapter
        }

        override fun bind(data: Pair<String, List<Book>>) {
            binding.titleTv.text = data.first
            easyAdapter.setItems(
                ItemList.create()
                    .addAll(data.second, bookItemController)
            )
        }

    }
}