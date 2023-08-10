package com.anandbibek.web.notifications.di

import android.content.Context
import com.anandbibek.web.notifications.WebNotificationApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* setup for global context injection ... something like that.. */
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): WebNotificationApplication {
        return app as WebNotificationApplication
    }
}