package com.anandbibek.web.notifications.ui.homeview

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.model.Notice
import java.net.URL

@Composable
fun ListWithWebNotices(uiState: HomeUiState) {
    when (uiState) {
        is HomeUiState.StaticSites -> {
            when (uiState.noticeList.isNullOrEmpty()) {

                true -> {
                    EmptyPlaceholder(uiState)
                }

                false -> {
                    NoticeList(notices = uiState.noticeList)
                }
            }
        }

        else -> {
            EmptyPlaceholder(uiState)
        }
    }
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

@Composable
fun NoticeList(notices: List<Notice>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(notices.size) { index ->
            val webNotice = notices[index]
            WebNoticeItemCard(webNotice)
        }

    }
}

@Composable
fun WebNoticeItemCard(notice: Notice) {

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                _ -> // Callback when the activity is closed, if required
        }

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.url.toString()))
                    launcher.launch(intent)
                }
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notice.title,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notice.data,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notice.time,
                    style = MaterialTheme.typography.labelSmall
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
fun PreviewListWithWebNotices() {
    val notices = listOf(
        Notice(
            title = "Web Notice 1",
            data = "Data for Web Notice 1",
            url = URL("https://example.com/webnotice1"),
            time = "2 mins ago",
            isStarred = true
        ),
        Notice(
            title = "Web Notice 2",
            data = "Data for Web Notice 2",
            url = URL("https://example.com/webnotice2"),
            time = "1 month ago",
            isStarred = false
        ),
        // Add more WebNotice items here as needed
    )
    NoticeList(notices = notices)
}