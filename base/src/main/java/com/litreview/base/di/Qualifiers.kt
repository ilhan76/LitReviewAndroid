package com.litreview.base.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNavGraph

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotAuthNavGraph