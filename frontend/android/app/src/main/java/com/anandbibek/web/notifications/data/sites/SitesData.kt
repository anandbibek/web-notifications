package com.anandbibek.web.notifications.data.sites

import com.anandbibek.web.notifications.R
import com.anandbibek.web.notifications.domain.model.Page
import com.anandbibek.web.notifications.domain.model.ParsLane
import com.anandbibek.web.notifications.domain.model.Site

val siteList = listOf(
    Site(
        id = "tpsc",
        name = "TPSC (Tripura)",
        description = "Tripura Public Services Commission",
        icon = R.drawable.pillars,
        url = "https://tpsc.tripura.gov.in/",
        pages = listOf(
            Page(
                name = "Notices",
                url = "https://tpsc.tripura.gov.in/",
                parsLane = ParsLane(
                    metaKey = "BASE",
                    selector = "ul#whats-new li",
                    parsLanes = listOf(
                        ParsLane(
                            metaKey = "text",
                            selector = "a:eq(0)"
                        ),
                        ParsLane(
                            metaKey = "link",
                            selector = "*:eq(0)"
                        )
                    )
                )
            )
        )
    ),
    Site(
        id = "tuv",
        name = "Tripura University",
        description = "State Central University of Tripura",
        url = "https://tripurauniv.ac.in/Page/Notification",
        icon = R.drawable.tuv_logo,
        pages = listOf(
            Page(
                name = "Notification",
                url = "https://tripurauniv.ac.in/Page/Notification",
                parsLane = ParsLane(
                    metaKey = "BASE",
                    selector = "table#TblNotification tbody tr",
                    parsLanes = listOf(
                        ParsLane(
                            metaKey = "text",
                            selector = "td:eq(1)"
                        ),
                        ParsLane(
                            metaKey = "link",
                            selector = "td:eq(2)"
                        )
                    )
                )
            ),
            Page(
                name = "Employment",
                url = "https://tripurauniv.ac.in/Page/EmploymentNews",
                parsLane = ParsLane(
                    metaKey = "BASE",
                    selector = "table#TblEmploymentNews tbody tr",
                    parsLanes = listOf(
                        ParsLane(
                            metaKey = "text",
                            selector = "td:eq(1)"
                        ),
                        ParsLane(
                            metaKey = "link",
                            selector = "td:eq(2)"
                        )
                    )
                )
            ),
            Page(
                name = "Seminars",
                url = "https://tripurauniv.ac.in/Page/AllSeminarlist",
                parsLane = ParsLane(
                    metaKey = "BASE",
                    selector = "table#TblSeminarConfarenceWorkshop tbody tr",
                    parsLanes = listOf(
                        ParsLane(
                            metaKey = "text",
                            selector = "td:eq(1)"
                        ),
                        ParsLane(
                            metaKey = "link",
                            selector = "td:eq(2)"
                        )
                    )
                )
            ),
        )
    ),
    Site(
        id = "riseq",
        name = "National Center for Seismology",
        description = "NCS is the nodal agency of GoI for monitoring earthquake activity in the country.",
        url = "https://seismo.gov.in/",
        icon = R.drawable.earthquake
        ,
        pages = listOf(
            Page(
                name = "Latest Earthquakes",
                url = "https://riseq.seismo.gov.in/riseq/earthquake",
                parsLane = ParsLane(
                    metaKey = "BASE",
                    selector = "ul.sidebar-nav li",
                    parsLanes = listOf(
                        ParsLane(
                            metaKey = "text",
                            selector = "*:eq(0)"
                        )
                    )
                )
            )
        )
    )
    // Add more items here as needed
)