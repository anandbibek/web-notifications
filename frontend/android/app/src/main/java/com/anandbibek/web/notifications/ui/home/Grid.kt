package com.anandbibek.web.notifications.ui.home

import CustomData
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anandbibek.web.notifications.ui.theme.WebNotificationsTheme
import customDataList

@Composable
fun GridWithCustomData(customDataList: List<CustomData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Two items per row
        contentPadding = PaddingValues(16.dp)
    ) {
        items(customDataList.size) { index ->
            val item = customDataList[index]
            GridItemCard(item.name, item.description, item.icon, item.url)
        }
    }
}


@Preview
@Composable
fun PreviewGridWithCustomData() {
    WebNotificationsTheme {
        GridWithCustomData(customDataList = customDataList)
    }
}