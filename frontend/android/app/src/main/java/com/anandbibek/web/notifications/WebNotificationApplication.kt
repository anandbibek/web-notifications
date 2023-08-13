package com.anandbibek.web.notifications

import android.app.Application
import com.anandbibek.web.notifications.data.AppContainer
import com.anandbibek.web.notifications.data.AppContainerImpl
import com.anandbibek.web.notifications.data.notices.NoticesRepositoryFactory
import com.anandbibek.web.notifications.data.notices.NoticesRepositoryFactoryImpl
import com.anandbibek.web.notifications.data.notices.impl.site.EmptyRepo
import com.anandbibek.web.notifications.data.notices.impl.site.TPSCRepo
import com.anandbibek.web.notifications.data.notices.impl.site.TripuraUniversityRepo
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

    //@Inject
    private lateinit var noticesRepositoryFactory: NoticesRepositoryFactory;


    @Inject
    lateinit var staticSitesRepository: SitesRepository;

    @Inject lateinit var tpscRepo: TPSCRepo;
    @Inject lateinit var tuvRepo: TripuraUniversityRepo;
    @Inject lateinit var emptyRepo: EmptyRepo;

    override fun onCreate() {
        super.onCreate()
        noticesRepositoryFactory = NoticesRepositoryFactoryImpl(
            hashMapOf(
                Pair("tpsc", tpscRepo),
                Pair("tuv", tuvRepo)
            ), emptyRepo
        );
        container = AppContainerImpl(this, staticSitesRepository, noticesRepositoryFactory)
    }
}
