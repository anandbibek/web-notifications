package com.anandbibek.web.notifications

import android.app.Application
import com.anandbibek.web.notifications.data.AppContainer
import com.anandbibek.web.notifications.data.AppContainerImpl
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.data.sites.SitesRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WebNotificationApplication : Application() {
    companion object {
        const val WEB_NOTIFICATION_APP_URI = "https://anandbibek.com/webnotifications"
    }

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    @Inject
    lateinit var staticSitesRepository: SitesRepository;

    @Inject
    lateinit var liveNoticesRepository: NoticesRepository;

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this, staticSitesRepository, liveNoticesRepository)
    }
}
