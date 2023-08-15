package com.anandbibek.web.notifications.di

import com.anandbibek.web.notifications.data.notices.impl.LiveNoticesRepository
import com.anandbibek.web.notifications.data.parser.NOPParser
import com.anandbibek.web.notifications.data.parser.site.TPSCParser
import com.anandbibek.web.notifications.data.parser.site.TripuraUniversityParser
import com.anandbibek.web.notifications.data.sites.SitesRepository
import com.anandbibek.web.notifications.data.sites.impl.ListedSitesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideStaticSitesRepository() : SitesRepository {
        return ListedSitesRepository()
    }

    @Singleton
    @Provides
    fun liveNoticesRepository() : LiveNoticesRepository {
        return LiveNoticesRepository()
    }

/*    @Singleton
    @Provides
    fun provideNoticesRepositoryFactory() : NoticesRepositoryFactory {
        return NoticesRepositoryFactoryImpl()
    }*/

    @Singleton
    @Provides
    fun provideTPSCParser() : TPSCParser {
        return TPSCParser()
    }

    @Singleton
    @Provides
    fun provideTripuraUniversityParser() : TripuraUniversityParser {
        return TripuraUniversityParser()
    }

    @Singleton
    @Provides
    fun provideNoParser() : NOPParser {
        return NOPParser()
    }
}