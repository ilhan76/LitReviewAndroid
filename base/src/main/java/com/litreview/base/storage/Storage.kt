package com.litreview.base.storage

interface Storage {

    fun getString(key: String): String

    fun setString(key: String, value: String)

    fun removeKey(vararg keys: String)

    fun clear()
}
