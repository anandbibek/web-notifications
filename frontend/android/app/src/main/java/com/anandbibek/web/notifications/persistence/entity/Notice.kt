package com.anandbibek.web.notifications.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notice (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "pageName") var pageName: String,
    @ColumnInfo(name = "starred") var isStarred: Boolean = false,
    @ColumnInfo(name = "time") val time: Long = System.currentTimeMillis()
)