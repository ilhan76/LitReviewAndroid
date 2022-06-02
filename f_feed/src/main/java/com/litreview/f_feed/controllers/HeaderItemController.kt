package com.litreview.f_feed.controllers

import android.view.ViewGroup
import com.litreview.f_feed.R
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

class HeaderItemController : NoDataItemController<HeaderItemController.Holder>() {

    inner class Holder(parent: ViewGroup) : BaseViewHolder(parent, R.layout.item_feed_header)

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)
}