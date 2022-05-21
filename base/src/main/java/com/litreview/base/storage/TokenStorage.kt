package com.litreview.base.storage

import com.litreview.base.data.TokenInfo

private const val KEY_ACCESS_TOKEN = "FB2671MKN67284UH3NIS284699E161"

class TokenStorage(private val storage: Storage) {

    var accessToken: String
        get() = storage.getString(KEY_ACCESS_TOKEN)
        set(value) = storage.setString(KEY_ACCESS_TOKEN, value)

    /**
     * Сохранение токенов пользователя
     */
    fun saveTokens(tokenInfo: TokenInfo) {
        accessToken = tokenInfo.accessToken
    }

    /**
     * Очистка хранилища
     */
    fun clearTokens() {
        storage.removeKey(KEY_ACCESS_TOKEN)
    }
}
