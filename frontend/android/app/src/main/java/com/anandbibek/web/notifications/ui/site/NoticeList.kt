package com.anandbibek.web.notifications.ui.site

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbibek.web.notifications.model.WebNotice
import java.net.URL

@Composable
fun ListWithWebNotices(webNotices: List<WebNotice>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(webNotices.size) { index ->
            val webNotice = webNotices[index]
            WebNoticeItemCard(webNotice)
        }
    }
}

@Composable
fun WebNoticeItemCard(webNotice: WebNotice) {

        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = webNotice.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = webNotice.data,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = webNotice.time,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Icon(
                    imageVector = if (webNotice.isStarred) Icons.Filled.Star else Icons.Default.Star,
                    contentDescription = null,
                    tint = if (webNotice.isStarred) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

}

@Preview
@Composable
fun PreviewListWithWebNotices() {
    val webNotices = listOf(
        WebNotice(
            title = "Web Notice 1",
            data = "Data for Web Notice 1",
            url = URL("https://example.com/webnotice1"),
            time = "2 mins ago",
            isStarred = true
        ),
        WebNotice(
            title = "Web Notice 2",
            data = "Data for Web Notice 2",
            url = URL("https://example.com/webnotice2"),
            time = "1 month ago",
            isStarred = false
        ),
        // Add more WebNotice items here as needed
    )
    ListWithWebNotices(webNotices = webNotices)
}