package com.litreview.f_feed.controllers

import android.view.ViewGroup
import com.litreview.f_feed.R
import com.litreview.f_feed.databinding.ItemFeedHeaderBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class HeaderItemController : BindableItemController<String, HeaderItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    override fun getItemId(data: String) = data

    inner class Holder(parent: ViewGroup) : BindableViewHolder<String>(parent, R.layout.item_feed_header) {

        private val vb = ItemFeedHeaderBinding.bind(itemView)

        override fun bind(data: String?) {
            vb.titleHelloTv.text = itemView.context.getString(
                R.string.hello_title_text,
                data
            )
        }
    }
}