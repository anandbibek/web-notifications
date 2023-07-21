package com.anandbibek.web.notifications.data.sites
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.model.Site

val siteList = listOf(
    Site(
        name = "TPSC (Tripura)",
        description = "Tripura Public Services Commission",
        icon = R.drawable.service_24_hour,
        url = "https://tpsc.tripura.gov.in/"
    ),
    Site(
        name = "Item 2",
        description = "Description for Item 2",
        icon = R.drawable.service_24_hour,
        url = "https://example.com/item2"
    ),
    Site(
        name = "Item 3",
        description = "Description for Item 3",
        icon = R.drawable.service_24_hour,
        url = "https://example.com/item3"
    ),
    // Add more items here as needed
)