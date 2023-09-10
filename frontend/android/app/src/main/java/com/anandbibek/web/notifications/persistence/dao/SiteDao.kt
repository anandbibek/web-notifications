package com.anandbibek.web.notifications.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.anandbibek.web.notifications.persistence.entity.Site

@Dao
interface SiteDao {
    @Query("SELECT * FROM site")
    fun getAll(): List<Site>

    @Insert
    fun insertAll(vararg siteMetadata: Site)

    @Delete
    fun delete(site: Site)
}