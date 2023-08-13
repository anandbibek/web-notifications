package com.anandbibek.web.notifications.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.containerColor
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TabScreen(
    tabs: List<String>,
    tabIndex: Int,
    onTabChange: (Int) -> Unit
) {
    //var tabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = contentColorFor(containerColor)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { onTabChange(index) }
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewTabScreen() {
    val tabs = listOf("Home", "About", "Settings", "More", "Something", "Everything")
    TabScreen(tabs = tabs, tabIndex = 0, onTabChange = {})
}