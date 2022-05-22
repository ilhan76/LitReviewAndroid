package com.litreview.i_network

object Urls {

    const val LOCAL_API_URL = "http://localhost:5000/api/"
    const val PROD_API_URL = "" //todo - после разворачивания на хостинге

    object Auth {
        private const val AUTH = "auth"

        const val LOGIN = "$AUTH/login"
        const val REGISTER = "$AUTH/register"
    }
}