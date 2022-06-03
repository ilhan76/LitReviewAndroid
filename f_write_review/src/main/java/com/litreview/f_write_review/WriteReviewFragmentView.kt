package com.litreview.f_write_review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.f_write_review.databinding.FragmentWriteReviewBinding

class WriteReviewFragmentView : Fragment(R.layout.fragment_write_review) {

    private val vb by viewBinding(FragmentWriteReviewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        with(vb.writeReviewToolbar.toolbar) {
            setNavigationIcon(com.litreview.base.R.drawable.ic_back)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}