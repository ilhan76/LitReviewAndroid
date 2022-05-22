package com.litreview.base.validation

interface ValidationResultEvent {

    val resultList: List<ValidationResult>

    val isSuccessful: Boolean
        get() = resultList.all { it.isSuccessful() }
}