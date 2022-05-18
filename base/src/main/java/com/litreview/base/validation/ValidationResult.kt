package com.litreview.base.validation

sealed class ValidationResult {

    object Success : ValidationResult()

    data class Failure(
        val messageRes: Int
    ) : ValidationResult()

}

fun ValidationResult.getErrorMessage(): Int {
    return if (this is ValidationResult.Failure) {
        this.messageRes
    } else -1
}