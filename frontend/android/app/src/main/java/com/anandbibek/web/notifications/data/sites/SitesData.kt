package com.anandbibek.web.notifications.data.sites

import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.model.Page
import com.anandbibek.web.notifications.model.Site

val siteList = listOf(
    Site(
        id = "tpsc",
        name = "TPSC (Tripura)",
        description = "Tripura Public Services Commission",
        icon = R.drawable.pillars,
        url = "https://tpsc.tripura.gov.in/",
        parser = "tpsc",
        pages = listOf(
            Page(
                name = "Notices",
                url = "https://tpsc.tripura.gov.in/",
                parser = "tpsc",
                parseTree = "ul#whats-new li"
            )
        )
    ),
    Site(
        id = "tuv",
        name = "Tripura University",
        description = "State Central University of Tripura",
        url = "https://tripurauniv.ac.in/Page/Notification",
        icon = R.drawable.tuv_logo,
        parser = "tuv",
        pages = listOf(
            Page(
                name = "Notification",
                url = "https://tripurauniv.ac.in/Page/Notification",
                parser = "tuv",
                parseTree = "table#TblNotification tbody tr"
            ),
            Page(
                name = "Employment",
                url = "https://tripurauniv.ac.in/Page/EmploymentNews",
                parser = "tuv",
                parseTree = "table#TblEmploymentNews tbody tr"
            ),
            Page(
                name = "Seminars",
                url = "https://tripurauniv.ac.in/Page/AllSeminarlist",
                parser = "tuv",
                parseTree = "table#TblSeminarConfarenceWorkshop tbody tr"
            ),
        )
    )
    // Add more items here as needed
)