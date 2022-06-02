package com.litreview.f_feed.controllers

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.litreview.base.data.domain.Book
import com.litreview.base.databinding.ItemBookBinding
import com.litreview.f_feed.R
import com.litreview.f_feed.databinding.ItemHorizontalBooksListBinding
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class HorizontalBooksListItemController(
    private val onBookClickAction: (Book?) -> Unit,
    private val title: String
) : BindableItemController<List<Book>, HorizontalBooksListItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent, title)

    override fun getItemId(data: List<Book>) = data.hashCode()

    inner class Holder(
        parent: ViewGroup,
        title: String
    ) : BindableViewHolder<List<Book>>(parent, R.layout.item_horizontal_books_list) {

        private val binding = ItemHorizontalBooksListBinding.bind(itemView)
        private val easyAdapter = EasyAdapter()
        private val itemController = ItemController(onBookClickAction)

        init {
            binding.titleTv.text = title
            binding.booksRv.adapter = easyAdapter
        }

        override fun bind(data: List<Book>) {
            easyAdapter.setItems(
                ItemList.create()
                    .addAll(data, itemController)
            )
        }

    }

    private class ItemController(
        private val onBookClickAction: (Book?) -> Unit
    ) : BindableItemController<Book, ItemController.Holder>() {

        override fun createViewHolder(parent: ViewGroup) = Holder(parent)

        override fun getItemId(data: Book) = data.id

        inner class Holder(
            parent: ViewGroup
        ) : BindableViewHolder<Book>(parent, com.litreview.base.R.layout.item_book) {

            private val binding = ItemBookBinding.bind(itemView)
            private var book: Book? = null

            init {
                binding.itemBookCard.setOnClickListener {
                    onBookClickAction(book)
                }
            }

            override fun bind(data: Book) {
                book = data
                Glide.with(binding.root)
                    .load(
                        data.imageUrl.takeIf {
                            it.isNotEmpty()
                        } ?: com.litreview.base.R.drawable.book_cover
                    ).into(binding.itemBookPoster)
                binding.itemBookName.text = data.title
                binding.itemBookAuthor.text = itemView.context.getString(
                    com.litreview.base.R.string.pattern_author_name,
                    data.author?.firstName,
                    data.author?.middleName,
                    data.author?.lastName
                )
            }

        }
    }
}