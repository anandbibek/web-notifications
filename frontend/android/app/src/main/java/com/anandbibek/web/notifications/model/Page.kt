package com.anandbibek.web.notifications.model

data class Page(
    val name: String,
    val icon: Int? = null,
    val url: String,
    val parser: String,
    val parseTree: String,
)