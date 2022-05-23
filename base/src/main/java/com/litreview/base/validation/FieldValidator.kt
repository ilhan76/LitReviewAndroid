package com.litreview.base.validation

import com.litreview.base.R
import javax.inject.Inject

class FieldValidator @Inject constructor() {

    private val emailRegex: Regex get() = EMAIL_PATTERN.toRegex()
    private val passwordRegex: Regex get() = PASSWORD_PATTERN.toRegex()
    private val phoneRegex: Regex get() = PHONE_PATTERN.toRegex()

    private val successValidationMessageRes = -1

    fun validate(validationRequest: ValidationRequest): ValidationResult {
        val value = validationRequest.value

        val errorMessage = when (validationRequest.fieldType) {
            ValidationFieldType.EMAIL -> validateEmail(value)
            ValidationFieldType.PASSWORD -> validatePassword(value)
            ValidationFieldType.PHONE -> validatePhone(value)
            else -> validateNotEmptyField(value)
        }
        return if (errorMessage == successValidationMessageRes) {
            ValidationResult.Success(validationRequest.fieldType)
        } else {
            ValidationResult.Failure(validationRequest.fieldType, errorMessage)
        }
    }

    private fun validateEmail(value: String): Int {
        return when {
            value.isEmpty() -> R.string.validation_failure_empty
            !emailRegex.matches(value) -> R.string.validation_failure_bad_email
            else -> successValidationMessageRes
        }
    }

    private fun validatePassword(value: String): Int {
        return when {
            value.isEmpty() -> R.string.validation_failure_empty
            !passwordRegex.matches(value) -> R.string.validation_failure_bad_password
            else -> successValidationMessageRes
        }
    }

    private fun validatePhone(value: String): Int {
        return when {
            value.isEmpty() -> R.string.validation_failure_empty
            !phoneRegex.matches(value) -> R.string.validation_failure_bad_phone
            else -> successValidationMessageRes
        }
    }

    private fun validateNotEmptyField(value: String): Int {
        return when {
            value.isEmpty() -> R.string.validation_failure_empty
            else -> successValidationMessageRes
        }
    }

    companion object {
        private const val EMAIL_PATTERN =
            "(?:[a-zA-Z0-9!#$№%&'*+=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]| 2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]: (?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
        private const val PASSWORD_PATTERN =
            "(?=^.{8,}\$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*\$"
        private const val PHONE_PATTERN = "[+]?[78]?[() 0-9-]+"
//        private const val PHONE_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$"
    }
}