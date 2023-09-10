package com.anandbibek.web.notifications.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.anandbibek.web.notifications.persistence.entity.Notice

@Dao
interface NoticeDao {
    @Query("SELECT * FROM notice")
    fun getAll(): List<Notice>

    @Insert
    fun insertAll(vararg notice: Notice)

    @Delete
    fun delete(notice: Notice)
}