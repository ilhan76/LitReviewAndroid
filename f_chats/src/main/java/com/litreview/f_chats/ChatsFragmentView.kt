package com.litreview.f_chats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.litreview.f_chats.databinding.FragmentChatsBinding

class ChatsFragmentView : Fragment(R.layout.fragment_chats) {

    private val vb by viewBinding<FragmentChatsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() = with(vb.chatsToolbar.toolbar) {
        title = getString(com.litreview.base.R.string.chats_toolbar_text)
        setNavigationOnClickListener { requireActivity().onBackPressed() }
        setNavigationIcon(com.litreview.base.R.drawable.ic_back)
    }
}