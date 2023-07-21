package com.anandbibek.web.notifications.model

import java.net.URL

data class Notice(
    val title: String,
    val data: String,
    val url: URL,
    val time: String,
    var isStarred: Boolean = false
)
