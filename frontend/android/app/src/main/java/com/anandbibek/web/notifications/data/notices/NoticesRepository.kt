package com.anandbibek.web.notifications.data.notices

import android.content.Context
import android.util.Log
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


interface NoticesRepository {

    companion object {
        private val TAG = this::class.simpleName
    }

    /* fetch list of notices */
    suspend fun fetchOnline(site: Site): List<Notice>
    fun getRepoName(site: Site): String

    fun getPersistenceFilename(site: Site): String {
        return getRepoName(site) + ".notices.json"
    }

    fun persist(context: Context, site: Site, data: List<Notice>) {
            try {
                // Create a file in the private directory to store the data
                val file = File(context.filesDir, getPersistenceFilename(site))

                // Write the data to the file
                file.writeText(Json.encodeToString(data))

            } catch (e: Exception) {
                Log.e(TAG, "Persist failed for " + site.id, e)
            }

    }

    suspend fun get(context: Context, site: Site): List<Notice> {
        /*return fetch() { webNotices ->
            persist(context, webNotices) // Persist the data
            webNotices // Emit the data downstream
        }*/

        val notices = fetchOnline(site)
        persist(context, site, notices)
        return  notices
    }
}