package com.litreview.base

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // Creating an extended library configuration.
        val config = YandexMetricaConfig.newConfigBuilder("863bb340-d379-43c7-9835-7e5f2dbf0086").build()
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(applicationContext, config)
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this)
        YandexMetrica.reportEvent("Test", "Test")
    }
}