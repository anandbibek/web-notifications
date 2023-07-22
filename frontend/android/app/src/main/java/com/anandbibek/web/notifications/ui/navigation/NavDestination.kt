package com.anandbibek.web.notifications.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.anandbibek.web.notifications.R

sealed class NavDestinations(val route: String, @StringRes val resourceId: Int, val ico: ImageVector) {
    object Route {
        const val HOME_ROUTE = "home"
        const val STARRED_ROUTE = "starred"
    }
    object Home : NavDestinations(Route.HOME_ROUTE, R.string.home, Icons.Filled.Home)
    object Starred : NavDestinations(Route.STARRED_ROUTE, R.string.starred, Icons.Filled.Star)
}

val navbarItems = listOf(
    NavDestinations.Home,
    NavDestinations.Starred,
)