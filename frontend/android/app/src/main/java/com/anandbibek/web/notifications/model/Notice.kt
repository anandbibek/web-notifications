package com.anandbibek.web.notifications.model

import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val id: Int,
    val title: String,
    val data: String,
    val url: String,
    val time: Long,
    var isStarred: Boolean = false
)
