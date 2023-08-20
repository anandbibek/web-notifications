package com.anandbibek.web.notifications.ui.homeview

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import com.anandbibek.web.notifications.model.ParsLane
import com.anandbibek.web.notifications.model.Site
import com.anandbibek.web.notifications.ui.widgets.TabScreen
import com.anandbibek.web.notifications.ui.widgets.TimeAgoFormatted

@Composable
fun ListWithWebNotices(
    uiState: HomeUiState.StaticSites, onSelectSite: (Site) -> Unit
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    var isSearchOpen by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var tabIndex by remember { mutableStateOf(0) }
    //val tabs = listOf("Home", "About", "Settings", "More", "Something", "Everything")

    uiState.selectedSite?.let {
        Column {
            //AnimatedVisibility (
            if(
                isSearchOpen
                //enter = fadeIn() + expandHorizontally(),
                //exit = fadeOut() + shrinkHorizontally()
            ) {
                SearchBox(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { searchQuery = it},
                    onSearchClose = {
                        searchQuery = ""
                        isSearchOpen = false
                    })
            }
            if (!isSearchOpen) {
                HeaderBox(site = it, onSearchOpen = { isSearchOpen = true })

                TabScreen(
                    tabs = it.pages.map { it.name },
                    tabIndex = tabIndex,
                    onTabChange = { tabIndex = it }
                )
            }
            NoticeList(uiState, launcher, it, onSelectSite, searchQuery, tabIndex)
        }
    }


}

@Composable
fun HeaderBox(
    site: Site, onSearchOpen: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween

    ) {
        if (site.icon != null) {
            Image(
                painter = painterResource(id = site.icon),
                contentDescription = site.name,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(58.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = site.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp),
                maxLines = 1
            )

            Text(
                text = site.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                fontWeight = FontWeight.Light
            )
        }

        IconButton(
            onClick = onSearchOpen,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}

@Composable
fun SearchBox(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchClose: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween

    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newQuery ->
                onSearchQueryChanged(newQuery)
            },

            leadingIcon = {
                Icon(
                    Icons.Default.Search, contentDescription = "Localized description"
                )
            },
            trailingIcon = {
                IconButton(onClick = onSearchClose) {
                    Icon(
                        Icons.Default.Close, contentDescription = "Clear"
                    )
                }
            },
            placeholder = { Text(text = "Search") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoticeList(
    uiState: HomeUiState.StaticSites,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    selectedSite: Site,
    onSelectSite: (Site) -> Unit,
    searchQuery: String,
    tabIndex: Int
) {

    //var refreshing by remember { mutableStateOf(uiState.isLoading) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onSelectSite(selectedSite) })

    //val noticesFlow = onRefreshNotices(selectedSite)
    val noticeList by uiState.noticeList.collectAsState(initial = emptyList())
    val filteredList = noticeList.filter { notice ->
        if (searchQuery.isEmpty()) {
            notice.pageName.equals(selectedSite.pages[tabIndex].name, ignoreCase = true)
        } else {
            notice.title.contains(searchQuery, ignoreCase = true)
                    || notice.data.contains(searchQuery, ignoreCase = true)
        }
    }

    Box(
        Modifier.pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(count = filteredList.size, key = { filteredList[it].id }, itemContent = { index ->
                WebNoticeItemCard(filteredList[index], launcher)

                // Add a divider after each item except for the last one
                if (index < filteredList.size - 1) {
                    Divider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.09f),
                        thickness = Dp.Hairline,
                    )
                }
            })

        }
        PullRefreshIndicator(
            uiState.isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter)
        )

    }

}

@Composable
fun WebNoticeItemCard(
    notice: Notice, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {


    Box {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.url))
                    launcher.launch(intent)
                }
                .padding(16.dp)) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TimeAgoFormatted(
                    time = notice.time,
                    style = MaterialTheme.typography.labelSmall,
                    prefixText = "Updated "
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notice.title, style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notice.data,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Icon(
                imageVector = if (notice.isStarred) Icons.Filled.Star else Icons.Default.Star,
                contentDescription = null,
                tint = if (notice.isStarred) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier.size(12.dp)
            )
        }
    }

}

@Preview
@Composable
fun PreviewHeaderBox() {
    HeaderBox(site = Site(
        id = "testSite",
        name = "TEST",
        description = "Description of the object",
        icon = R.drawable.pillars,
        url = "https://tpsc.tripura.gov.in/",
        parser = "test",
        pages = listOf(
            Page(name = "Notices", url = "https://tpsc.tripura.gov.in/", parsLane = ParsLane("", ""))
        )
    ), onSearchOpen = {})
}

@Preview
@Composable
fun PreviewSearchBox() {
    SearchBox(searchQuery = "", onSearchQueryChanged = {}) {

    }
}

/*
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewListWithWebNotices() {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    val notices = listOf(
        Notice(
            id = 1,
            title = "Web Notice 1",
            data = "Data for Web Notice 1",
            url = URL("https://example.com/webnotice1"),
            time = 44,
            isStarred = true
        ),
        Notice(
            id = 2,
            title = "Web Notice 2",
            data = "Data for Web Notice 2",
            url = URL("https://example.com/webnotice2"),
            time = 4444444444,
            isStarred = false
        ),
        // Add more WebNotice items here as needed
    )
    NoticeList(notices = notices, launcher, false)
}*/
