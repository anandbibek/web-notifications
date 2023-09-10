package com.anandbibek.web.notifications.data.notices.impl

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.domain.mapper.NoticeMapper
import com.anandbibek.web.notifications.domain.model.Notice
import com.anandbibek.web.notifications.domain.model.Site
import com.anandbibek.web.notifications.persistence.AppDatabase

interface DatabaseRepository: NoticesRepository {

    companion object {
        private val TAG = this::class.simpleName
    }

    override fun persistOffline(context: Context, site: Site, data: List<Notice>) {
        try {
            val db = Room.databaseBuilder(
                context, AppDatabase::class.java, "database-name"
            ).build()
            db.noticeDao().insertAll(*data.map { NoticeMapper().mapFromModel(it) }.toTypedArray())

        } catch (e: Exception) {
            Log.e(TAG, "Persist failed for " + site.id, e)
        }
    }

    override fun fetchOffline(context: Context, site: Site): List<Notice> {
        return try {
            val db = Room.databaseBuilder(
                context, AppDatabase::class.java, "database-name"
            ).build()
            db.noticeDao().getAll().map { NoticeMapper().mapToModel(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Fetch failed for " + site.id, e)
            emptyList()
        }
    }
}