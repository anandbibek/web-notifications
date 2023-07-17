package com.anandbibek.web.notifications.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import com.anandbibek.web.notifications.model.Site
import com.anandbibek.web.notifications.ui.theme.WebNotificationsTheme
import siteList

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

@Composable
fun GridItemCard(name: String, description: String, icon: Int, url: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { /* TODO open new page */ },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = name,
                fontSize = 16.sp,
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
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

