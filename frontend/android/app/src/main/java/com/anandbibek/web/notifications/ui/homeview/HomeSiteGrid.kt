package com.anandbibek.web.notifications.ui.homeview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.data.sites.siteList
import com.anandbibek.web.notifications.model.Site
import com.anandbibek.web.notifications.ui.theme.WebNotificationsTheme


@Composable
fun SiteGridHomeScreen(
    uiState: HomeUiState,
    onRefreshSites: () -> Unit,
    onSelectSite: (Site) -> Unit,
    onErrorDismiss: (Long) -> Unit,
    homeListLazyListState: LazyListState,
    siteDetailLazyListStates: Map<String, LazyListState>,
    onSearchInputChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is HomeUiState.StaticSites -> {
            SiteGrid(siteList = uiState.siteList, onSelectSite)
        }

        is HomeUiState.NoSites -> {
            // if there are no posts, and no error, let the user refresh manually
            TextButton(
                onClick = onRefreshSites,
                modifier.fillMaxSize()
            ) {
                val msg = stringResource(id = R.string.site_load_failed) + uiState
                    .errorMessages.joinToString(". ")
                Text(
                    text = msg,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun SiteGrid(
    siteList: List<Site>,
    onSelectSite: (Site) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Two items per row
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(siteList.size) { index ->
            val item = siteList[index]
            GridItemCard(item, onSelectSite)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItemCard(
    site: Site,
    onSelectPost: (Site) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = { onSelectPost(site) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                if (site.icon != null) {
                    Image(
                        painter = painterResource(id = site.icon),
                        contentDescription = site.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(96.dp),
                        colorFilter = ColorFilter.tint(
                            MaterialTheme.colorScheme
                                .onPrimaryContainer
                        )
                    )
                } else {
                    Icon(
                        Icons.Rounded.List,
                        contentDescription = site.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .height(96.dp)
                    )
                }
            }
            Text(
                text = site.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = site.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}

@Preview
@Composable
fun PreviewGridCard() {
    WebNotificationsTheme() {
        GridItemCard(
            Site(
                id = "testSite", name = "TEST", description = "Description of the object",
                icon = R.drawable.pillars, url = "https://tpsc.tripura.gov.in/"
            ),
            onSelectPost = { /* nothing */ }
        )
    }
}


@Preview
@Composable
fun PreviewGridWithCustomData() {
    WebNotificationsTheme {
        SiteGrid(siteList = siteList) { /* nothing */ }
    }
}