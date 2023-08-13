package com.anandbibek.web.notifications.model

data class Site(
    val id: String,
    val name: String,
    val description: String,
    val icon: Int? = null,
    val url: String,
    val parser: String
)