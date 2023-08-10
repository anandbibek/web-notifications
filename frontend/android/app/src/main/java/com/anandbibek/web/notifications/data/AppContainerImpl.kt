package com.anandbibek.web.notifications.data

import android.content.Context
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.data.sites.SitesRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val staticSitesRepository: SitesRepository
    val liveNoticesRepository: NoticesRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 */
class AppContainerImpl(
    applicationContext: Context,
    override val staticSitesRepository: SitesRepository,
    override val liveNoticesRepository: NoticesRepository
) : AppContainer {

    //override val staticSitesRepository: SitesRepository = staticSitesRepository
    //override val liveNoticesRepository: NoticesRepository = liveNoticesRepository

}
