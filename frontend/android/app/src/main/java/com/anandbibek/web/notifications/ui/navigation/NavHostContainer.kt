/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anandbibek.web.notifications.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.anandbibek.web.notifications.WebNotificationApplication.Companion.WEB_NOTIFICATION_APP_URI
import com.anandbibek.web.notifications.data.AppContainer
import com.anandbibek.web.notifications.ui.homeview.HomeRoute
import com.anandbibek.web.notifications.ui.homeview.HomeViewModel
import com.anandbibek.web.notifications.ui.navigation.NavDestinations.Route.HOME_ROUTE
import com.anandbibek.web.notifications.ui.navigation.NavDestinations.Route.STARRED_ROUTE
import com.anandbibek.web.notifications.ui.starredview.StarredRoute
import com.anandbibek.web.notifications.ui.starredview.StarredViewModel

const val SITE_ID = "siteId"

@Composable
fun NavHostContainer(
    appContainer: AppContainer?,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = HOME_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern =
                        "$WEB_NOTIFICATION_APP_URI/$HOME_ROUTE?$SITE_ID={$SITE_ID}"
                }
            )
        ) { navBackStackEntry ->
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    sitesRepository = appContainer?.sitesRepository,
                    preSelectedSiteId = navBackStackEntry.arguments?.getString(SITE_ID)
                )
            )

            HomeRoute(
                viewModel = homeViewModel
            )
        }
        composable(STARRED_ROUTE) {
            val starredViewModel: StarredViewModel = viewModel(
                factory = StarredViewModel.provideFactory(appContainer?.noticesRepository)
            )
            StarredRoute(
                viewModel = starredViewModel
            )
        }
    }
}


