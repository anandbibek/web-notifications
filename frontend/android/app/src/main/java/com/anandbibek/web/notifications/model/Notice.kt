package com.anandbibek.web.notifications.model

import java.net.URL

data class Notice(
    val id: Int,
    val title: String,
    val data: String,
    val url: URL,
    val time: Long,
    var isStarred: Boolean = false
)
