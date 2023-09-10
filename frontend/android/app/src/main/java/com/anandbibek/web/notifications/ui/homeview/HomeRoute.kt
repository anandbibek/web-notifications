package com.anandbibek.web.notifications.ui.homeview

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anandbibek.web.notifications.domain.model.Site
import com.anandbibek.web.notifications.ui.homeview.HomeScreenType.Notices
import com.anandbibek.web.notifications.ui.homeview.HomeScreenType.Sites
import com.anandbibek.web.notifications.ui.homeview.HomeScreenType.SitesWithNotices

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    isExpandedScreen: Boolean,
    context: Context = LocalContext.current
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeRouteProxy(
        uiState = uiState,
        isExpandedScreen = isExpandedScreen,
        onSelectSite = { homeViewModel.selectSite(context, it) },
        onRefreshSites = { homeViewModel.refreshSites() },
        onErrorDismiss = { homeViewModel.errorShown(it) },
        onSearchInputChanged = { homeViewModel.onSearchInputChanged(it) },
        onBackToSites = { homeViewModel.backToSites() }
    )

}


/**
 * Displays the Home route.
 *
 * This composable is not coupled to any specific state management.
 *
 * @param uiState (state) the data to show on the screen
 * @param onRefreshSites (event) request a refresh of posts
 * @param onErrorDismiss (event) error message was shown
 */
@Composable
fun HomeRouteProxy(
    uiState: HomeUiState,
    isExpandedScreen: Boolean,
    onSelectSite: (Site) -> Unit,
    onRefreshSites: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onSearchInputChanged: (String) -> Unit,
    onBackToSites: () -> Unit,
) {
    // Construct the lazy list states for the list and the details outside of deciding which one to
    // show. This allows the associated state to survive beyond that decision, and therefore
    // we get to preserve the scroll throughout any changes to the content.
    val homeListLazyListState = rememberLazyListState()
    val siteDetailLazyListStates = when (uiState) {
        is HomeUiState.StaticSites -> uiState.siteList
        is HomeUiState.NoSites -> emptyList()
    }.associate { post ->
        key(post.id) {
            post.id to rememberLazyListState()
        }
    }

    when(getHomeScreenType(isExpandedScreen, uiState)) {
        SitesWithNotices -> {

        }

        Sites -> {
            SiteGridHomeScreen(
                uiState = uiState,
                onSelectSite = onSelectSite,
                onRefreshSites = onRefreshSites,
                onErrorDismiss = onErrorDismiss,
                homeListLazyListState = homeListLazyListState,
                siteDetailLazyListStates = siteDetailLazyListStates,
                onSearchInputChanged = onSearchInputChanged,
            )
        }

        Notices -> {
            ListWithWebNotices(
                uiState = uiState as HomeUiState.StaticSites,
                onSelectSite = onSelectSite,
                )
        }
    }



    // If we are just showing the detail, have a back press switch to the list.
    // This doesn't take anything more than notifying that we "interacted with the list"
    // since that is what drives the display of the feed
    BackHandler {
        onBackToSites()
    }
}


/**
 * A precise enumeration of which type of screen to display at the home route.
 *
 * There are 3 options:
 * - [SitesWithNotices], which displays both a list of all sites and notices from one site.
 * - [Sites], which displays just the list of all sites
 * - [Notices], which displays just a specific site's notices.
 */
private enum class HomeScreenType {
    SitesWithNotices,
    Sites,
    Notices
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getHomeScreenType(
    isExpandedScreen: Boolean,
    uiState: HomeUiState
): HomeScreenType = when (isExpandedScreen) {
    false -> {
        when (uiState) {
            is HomeUiState.StaticSites -> {
                if (uiState.isSiteOpen) {
                    Notices
                } else {
                    Sites
                }
            }

            is HomeUiState.NoSites -> Sites
        }
    }

    true -> SitesWithNotices
}