package com.litreview.i_network

object Urls {

    const val LOCAL_API_URL = "http://192.168.0.107:5000/"
    const val PROD_API_URL = "" //todo - после разворачивания на хостинге

    object Auth {
        private const val AUTH = "auth"

        const val LOGIN = "$AUTH/login"
        const val REGISTER = "$AUTH/register"
    }

    object Profile {
        const val PROFILE = "profile"

        const val MY_BOOKS = "$PROFILE/books"
        const val ADD_BOOK = "$PROFILE/book"
    }

    object Books {
        private const val BOOKS = "books"

        const val BEST_BOOKS = "$BOOKS?rate=4"
        const val NEW_BOOKS = "$BOOKS/news"
    }

    object Review {
        const val REVIEWS = "reviews"
    }
}