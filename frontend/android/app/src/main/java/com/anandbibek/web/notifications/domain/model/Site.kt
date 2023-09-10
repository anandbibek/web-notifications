package com.anandbibek.web.notifications.domain.model

data class Site(
    val id: String,
    val name: String,
    val description: String,
    val icon: Int? = null,
    val url: String,
    val pages: List<Page>
)