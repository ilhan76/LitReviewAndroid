package com.litreview.base.ui

import android.text.Editable
import android.text.TextWatcher
import com.litreview.base.util.EMPTY_STRING

class SimpleTextWatcher(
    val onTextChanged: (s: String) -> Unit
): TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
        onTextChanged(p0?.toString() ?: EMPTY_STRING)
    }

}