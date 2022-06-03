package com.litreview.f_feed.controllers

import android.view.ViewGroup
import com.litreview.f_feed.R
import com.litreview.f_feed.databinding.ItemSearchBinding
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class SearchItemController(
    private val onClick: () -> Unit
) : NoDataItemController<SearchItemController.Holder>() {
    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.item_search) {

        private val binding = ItemSearchBinding.bind(itemView)

        init {
            binding.searchButton.setOnClickListener {
                onClick()
            }
        }

    }
}
