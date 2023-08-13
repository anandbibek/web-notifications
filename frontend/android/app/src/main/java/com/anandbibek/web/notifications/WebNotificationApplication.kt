package com.anandbibek.web.notifications

import android.app.Application
import com.anandbibek.web.notifications.data.AppContainer
import com.anandbibek.web.notifications.data.AppContainerImpl
import com.anandbibek.web.notifications.data.notices.impl.LiveNoticesRepository
import com.anandbibek.web.notifications.data.parser.NoParser
import com.anandbibek.web.notifications.data.parser.ParserFactory
import com.anandbibek.web.notifications.data.parser.ParserFactoryImpl
import com.anandbibek.web.notifications.data.parser.site.TPSCParser
import com.anandbibek.web.notifications.data.parser.site.TripuraUniversityParser
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
    private lateinit var parserFactory: ParserFactory;


    @Inject
    lateinit var staticSitesRepository: SitesRepository;

    @Inject
    lateinit var liveNoticesRepository: LiveNoticesRepository;

    @Inject
    lateinit var tpscParser: TPSCParser;
    @Inject
    lateinit var tuvParser: TripuraUniversityParser;
    @Inject
    lateinit var noParser: NoParser;

    override fun onCreate() {
        super.onCreate()
        parserFactory = ParserFactoryImpl(
            hashMapOf(
                Pair("tpsc", tpscParser),
                Pair("tuv", tuvParser)
            ), noParser
        );
        container = AppContainerImpl(
            this, staticSitesRepository, liveNoticesRepository,
            parserFactory
        )
    }
}
