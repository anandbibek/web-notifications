package com.anandbibek.web.notifications.di

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.data.notices.impl.LiveNoticesRepository
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
    fun provideLiveNoticesRepository() : NoticesRepository {
        return LiveNoticesRepository()
    }
}