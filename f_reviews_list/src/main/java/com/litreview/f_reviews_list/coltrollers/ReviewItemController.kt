package com.litreview.f_reviews_list.coltrollers

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.litreview.base.data.domain.Review
import com.litreview.f_reviews_list.R
import com.litreview.f_reviews_list.databinding.ItemReviewBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class ReviewItemController : BindableItemController<Review, ReviewItemController.Holder>() {

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    override fun getItemId(data: Review) = data.id

    inner class Holder(parent: ViewGroup) :
        BindableViewHolder<Review>(parent, R.layout.item_review) {

        private val binding = ItemReviewBinding.bind(itemView)

        override fun bind(data: Review) {
            with(binding) {
                Glide.with(root)
                    .load(
                        data.userInfo?.avatarUrl.takeIf {
                            it?.isNotEmpty() == true
                        } ?: com.litreview.base.R.drawable.book_cover
                    ).into(reviewerAvatar)
                reviewerName.text = data.userName

                reviewText.text = data.text
                bookRating.rating = data.rate.toFloat()

            }
        }
    }
}