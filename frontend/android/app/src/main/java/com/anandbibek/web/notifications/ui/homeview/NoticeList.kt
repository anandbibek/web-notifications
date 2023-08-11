package com.anandbibek.web.notifications.ui.homeview

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import com.anandbibek.web.notifications.ui.widgets.TimeAgoFormatted

@Composable
fun ListWithWebNotices(
    uiState: HomeUiState.StaticSites,
    onSelectSite: (Site) -> Unit
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    NoticeList(uiState, launcher, uiState.isLoading, onSelectSite)


}

@Composable
fun EmptyPlaceholder(uiState: HomeUiState) {
// if there are no posts, and no error, let the user refresh manually
    TextButton(
        modifier = Modifier.fillMaxSize(),
        onClick = {}
    ) {
        val msg = stringResource(id = R.string.site_load_failed) + uiState
            .errorMessages.joinToString(". ")
        Text(
            text = msg,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoticeList(
    uiState: HomeUiState.StaticSites,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    loading: Boolean,
    onSelectSite: (Site) -> Unit
) {


    val pullRefreshState = rememberPullRefreshState(loading, {
        uiState.selectedSite?.let {
            onSelectSite(
                it
            )
        }
    })
    Box(
        Modifier.pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(
                count = uiState.noticeList.size,
                key = { uiState.noticeList[it].id },
                itemContent =
                { index ->
                    WebNoticeItemCard(uiState.noticeList[index], launcher)

                    // Add a divider after each item except for the last one
                    if (index < uiState.noticeList.size - 1) {
                        Divider(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.09f),
                            thickness = Dp.Hairline,
                        )
                    }
                }
            )

        }
        PullRefreshIndicator(loading, pullRefreshState, Modifier.align(Alignment.TopCenter))

    }

}

@Composable
fun WebNoticeItemCard(
    notice: Notice,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {


    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.url))
                    launcher.launch(intent)
                }
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TimeAgoFormatted(
                    time = notice.time,
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notice.title,
                    style = MaterialTheme.typography.bodySmall
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
