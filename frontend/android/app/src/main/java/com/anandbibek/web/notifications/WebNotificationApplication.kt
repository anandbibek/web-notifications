package com.anandbibek.web.notifications

import android.app.Application
import com.anandbibek.web.notifications.data.AppContainer
import com.anandbibek.web.notifications.data.AppContainerImpl

class WebNotificationApplication : Application() {
    companion object {
        const val WEB_NOTIFICATION_APP_URI = "https://anandbibek.com/webnotifications"
    }

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}
