package com.anandbibek.web.notifications.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val id: Int,
    val title: String,
    val data: String,
    val url: String,
    var pageName: String,
    var isStarred: Boolean = false,
    val time: Long = System.currentTimeMillis()
)
