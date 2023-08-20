package com.anandbibek.web.notifications.model

import kotlinx.serialization.Serializable

@Serializable
data class ParsLane(
    val selector: String,
    val metaKey: String,
    val parsLanes: List<ParsLane> = emptyList()
)
