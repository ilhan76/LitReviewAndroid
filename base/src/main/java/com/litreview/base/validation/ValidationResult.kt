package com.litreview.base.validation

sealed class ValidationResult(
    open val fieldType: ValidationFieldType
) {

    data class Success(
        override val fieldType: ValidationFieldType
    ) : ValidationResult(fieldType)

    data class Failure(
        override val fieldType: ValidationFieldType,
        val messageRes: Int
    ) : ValidationResult(fieldType)

}

fun ValidationResult.getErrorMessageRes(): Int {
    return if (this is ValidationResult.Failure) {
        this.messageRes
    } else -1
}

fun ValidationResult.getErrorMessageResOrNull(): Int? {
    return if (this is ValidationResult.Failure) {
        this.messageRes
    } else null
}

fun ValidationResult.isSuccessful() = this is ValidationResult.Success

fun ValidationResult.isFailure() = this is ValidationResult.Failure
