package com.anandbibek.web.notifications.data.sites
import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.model.Site

val siteList = listOf(
    Site(
        id = "tpsc",
        name = "TPSC (Tripura)",
        description = "Tripura Public Services Commission",
        icon = R.drawable.pillars,
        url = "https://tpsc.tripura.gov.in/",
        parser = "tpsc"
    ),
    Site(
        id = "tuv",
        name = "Tripura University",
        description = "Main Public Govt. University of Tripura",
        url = "https://tripurauniv.ac.in/Page/Notification",
        icon = R.drawable.tuv_logo,
        parser = "tuv"
    )
    // Add more items here as needed
)