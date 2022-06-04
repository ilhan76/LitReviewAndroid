package com.litreview.base.controller

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.litreview.base.data.domain.Book
import com.litreview.base.databinding.ItemBookBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class BookItemController(
    private val onBookClickAction: (Book?) -> Unit
) : BindableItemController<Book, BookItemController.Holder>() {

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
            binding.itemBookAuthor.text = data.authorName
        }
    }
}