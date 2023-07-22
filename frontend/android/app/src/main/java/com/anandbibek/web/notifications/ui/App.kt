package com.anandbibek.web.notifications.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anandbibek.web.notifications.data.AppContainer
import com.anandbibek.web.notifications.ui.navigation.NavDestinations
import com.anandbibek.web.notifications.ui.navigation.NavHostContainer
import com.anandbibek.web.notifications.ui.navigation.navbarItems
import com.anandbibek.web.notifications.ui.theme.WebNotificationsTheme


@Composable
fun NotificationsApp(appContainer: AppContainer?) {
    WebNotificationsTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: NavDestinations.Route.HOME_ROUTE

        Scaffold(
            bottomBar = {
                NavigationBar {
                    navbarItems.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    screen.ico,
                                    contentDescription = stringResource(screen.resourceId)
                                )
                            },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHostContainer(
                appContainer = appContainer,
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }

    }
}

@Preview
@Composable
fun PreviewNavBar() {
    WebNotificationsTheme {
        NotificationsApp(appContainer = null)
    }
}

