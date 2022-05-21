package com.litreview.base.storage

import android.content.SharedPreferences
import com.litreview.base.util.EMPTY_STRING

class SharedPrefStorage(
    private val sharedPreferences: SharedPreferences
) : Storage {

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, EMPTY_STRING) ?: EMPTY_STRING
    }

    override fun setString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun removeKey(vararg keys: String) {
        val editor = sharedPreferences.edit()
        keys.forEach { editor.remove(it) }
        editor.apply()
    }

    override fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}