package com.anandbibek.web.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.anandbibek.web.notifications.ui.homeview.NotificationsApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as WebNotificationApplication).container
        setContent {
            NotificationsApp(appContainer)
        }
    }
}

