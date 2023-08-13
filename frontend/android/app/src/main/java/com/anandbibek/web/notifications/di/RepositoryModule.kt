package com.anandbibek.web.notifications.di

import com.anandbibek.web.notifications.data.notices.impl.site.EmptyRepo
import com.anandbibek.web.notifications.data.notices.impl.site.TPSCRepo
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

/*    @Singleton
    @Provides
    fun provideNoticesRepositoryFactory() : NoticesRepositoryFactory {
        return NoticesRepositoryFactoryImpl()
    }*/

    @Singleton
    @Provides
    fun provideTPSCRepo() : TPSCRepo {
        return TPSCRepo()
    }

    @Singleton
    @Provides
    fun provideEmptyRepo() : EmptyRepo {
        return EmptyRepo()
    }
}