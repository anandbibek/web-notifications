package com.anandbibek.web.notifications.ui.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import java.util.concurrent.TimeUnit

@Composable
fun TimeAgoFormatted(
    time: Long,
    modifier: Modifier = Modifier,
    style: TextStyle
) {
    val timeAgo = getTimeAgoFormatted(time)

    Text(
        text = timeAgo,
        modifier = modifier,
        style = style
    )
}

@Preview
@Composable
fun TimeAgoFormattedPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            TimeAgoFormatted(
                System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

fun getTimeAgoFormatted(timeInMillis: Long): String {
    val currentTimeMillis = System.currentTimeMillis()
    val timeDiffMillis = currentTimeMillis - timeInMillis

    val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiffMillis)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiffMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(timeDiffMillis)
    val days = TimeUnit.MILLISECONDS.toDays(timeDiffMillis)

    return when {
        days >= 1 -> "$days days ago"
        hours >= 1 -> "$hours hours ago"
        minutes >= 1 -> "$minutes minutes ago"
        else -> "Now"
    }
}