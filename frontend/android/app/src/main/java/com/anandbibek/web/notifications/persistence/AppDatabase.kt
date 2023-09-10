package com.anandbibek.web.notifications.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anandbibek.web.notifications.persistence.dao.NoticeDao
import com.anandbibek.web.notifications.persistence.dao.SiteDao
import com.anandbibek.web.notifications.persistence.entity.Notice
import com.anandbibek.web.notifications.persistence.entity.Site

@Database(entities = [Site::class, Notice::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): SiteDao
    abstract fun noticeDao(): NoticeDao
}