package com.anandbibek.web.notifications.ui.homeview

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(homeViewModel: HomeViewModel) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeRouteGrid(
        uiState = uiState,
        //onSelectPost = { homeViewModel.selectArticle(it) },
        onRefreshPosts = { homeViewModel.refreshSites() },
        onErrorDismiss = { homeViewModel.errorShown(it) },
        onSearchInputChanged = { homeViewModel.onSearchInputChanged(it) }
    )

}


/**
 * Displays the Home route.
 *
 * This composable is not coupled to any specific state management.
 *
 * @param uiState (state) the data to show on the screen
 * @param onRefreshPosts (event) request a refresh of posts
 * @param onErrorDismiss (event) error message was shown
 */
@Composable
fun HomeRouteGrid(
    uiState: HomeUiState,
    //onSelectPost: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onSearchInputChanged: (String) -> Unit,
) {
    // Construct the lazy list states for the list and the details outside of deciding which one to
    // show. This allows the associated state to survive beyond that decision, and therefore
    // we get to preserve the scroll throughout any changes to the content.
    val homeListLazyListState = rememberLazyListState()
    val articleDetailLazyListStates = when (uiState) {
        is HomeUiState.StaticSites -> uiState.siteList
        is HomeUiState.NoSites -> emptyList()
    }.associate { post ->
        key(post.id) {
            post.id to rememberLazyListState()
        }
    }
    SiteGridHomeScreen(
        uiState = uiState,
        onRefreshPosts = onRefreshPosts,
        onErrorDismiss = onErrorDismiss,
        homeListLazyListState = homeListLazyListState,
        articleDetailLazyListStates = articleDetailLazyListStates,
        onSearchInputChanged = onSearchInputChanged,
    )


    // If we are just showing the detail, have a back press switch to the list.
    // This doesn't take anything more than notifying that we "interacted with the list"
    // since that is what drives the display of the feed
    /*BackHandler {
        onInteractWithFeed()
    }*/
}
