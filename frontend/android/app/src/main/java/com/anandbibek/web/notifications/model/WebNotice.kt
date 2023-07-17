package com.anandbibek.web.notifications.model

import java.net.URL

data class WebNotice(
    val title: String,
    val data: String,
    val url: URL,
    val time: String,
    var isStarred: Boolean
)
