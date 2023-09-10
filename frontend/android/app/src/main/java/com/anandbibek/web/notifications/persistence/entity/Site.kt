package com.anandbibek.web.notifications.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Site(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "metadata") val metadata: String
)