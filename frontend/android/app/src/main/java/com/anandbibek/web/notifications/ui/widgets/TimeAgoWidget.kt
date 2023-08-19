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
    style: TextStyle,
    prefixText: String = "",
) {
    val timeAgo = getTimeAgoFormatted(time, prefixText)

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
                System.currentTimeMillis() - TimeUnit.HOURS.toMillis(96),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

fun getTimeAgoFormatted(timeInMillis: Long, prefixText: String): String {
    val currentTimeMillis = System.currentTimeMillis()
    val timeDiffMillis = currentTimeMillis - timeInMillis

    //val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiffMillis)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiffMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(timeDiffMillis)
    val days = TimeUnit.MILLISECONDS.toDays(timeDiffMillis)

    return prefixText + when {
        days >= 1 -> "$days day${if (days > 1) "s" else ""} ago"
        hours >= 1 -> "$hours hour${if (hours > 1) "s" else ""} ago"
        minutes >= 1 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        else -> "Now"
    }
}