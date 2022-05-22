package com.litreview.base.mvi

sealed class Request<T> {

    class Loading<T> : Request<T>()
    data class Success<T>(internal val data: T) : Request<T>()
    data class Error<T>(internal val error: Throwable) : Request<T>()

    val isLoading: Boolean get() = this is Loading

    val isSuccess: Boolean get() = this is Success

    val isError: Boolean get() = this is Error

    fun getData(): T = (this as Success).data

    fun getDataOrNull(): T? = (this as? Success)?.data

    fun getError(): Throwable = (this as Error).error

    fun getErrorOrNull(): Throwable? = (this as? Error)?.error
}