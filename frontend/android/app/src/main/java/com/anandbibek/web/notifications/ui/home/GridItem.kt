package com.anandbibek.web.notifications.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.ui.theme.WebNotificationsTheme


@Composable
fun GridItemCard(name: String, description: String, icon: Int, url: String) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { /* TODO open new page */ }
            .aspectRatio(1f), // Maintain a square aspect ratio
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {

            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
            )
        }
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Gray
        )
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
