package com.anandbibek.web.notifications.data

import android.content.Context
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.data.notices.impl.LiveNoticesRepository
import com.anandbibek.web.notifications.data.sites.SitesRepository
import com.anandbibek.web.notifications.data.sites.impl.ListedSitesRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val noticesRepository: NoticesRepository
    val sitesRepository: SitesRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val noticesRepository: NoticesRepository by lazy {
        LiveNoticesRepository()
    }

    override val sitesRepository: SitesRepository by lazy {
        ListedSitesRepository()
    }
}
