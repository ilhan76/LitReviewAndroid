package com.litreview.base.data

fun interface Transformable<D> {
    fun transform(): D
}