package com.anandbibek.web.notifications.ui.homeview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    homeListLazyListState: LazyListState,
    articleDetailLazyListStates: Map<String, LazyListState>,
    onSearchInputChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is HomeUiState.StaticSites -> {
            SiteGrid(siteList = uiState.siteList)
        }

        is HomeUiState.NoSites -> {
            // if there are no posts, and no error, let the user refresh manually
            TextButton(
                onClick = onRefreshPosts,
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
fun SiteGrid(siteList: List<Site>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Two items per row
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(siteList.size) { index ->
            val item = siteList[index]
            GridItemCard(item.name, item.description, item.icon, item.url)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItemCard(name: String, description: String, icon: Int, url: String) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = { /*TODO*/}
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun PreviewGridCard() {
    WebNotificationsTheme() {
        GridItemCard(
            name = "TEST", description = "Description of the object", icon = R.drawable
                .service_24_hour, url = "https://tpsc.tripura.gov.in/"
        )
    }
}


@Preview
@Composable
fun PreviewGridWithCustomData() {
    WebNotificationsTheme {
        SiteGrid(siteList = siteList)
    }
}