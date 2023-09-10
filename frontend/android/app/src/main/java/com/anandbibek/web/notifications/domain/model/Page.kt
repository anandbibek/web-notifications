package com.anandbibek.web.notifications.domain.model

data class Page(
    val name: String,
    val icon: Int? = null,
    val url: String,
    val parsLane: ParsLane,
)