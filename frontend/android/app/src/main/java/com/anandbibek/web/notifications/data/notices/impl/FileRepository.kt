package com.anandbibek.web.notifications.data.notices.impl

import android.content.Context
import android.util.Log
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.domain.model.Notice
import com.anandbibek.web.notifications.domain.model.Site
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface FileRepository: NoticesRepository {

    companion object {
        private val TAG = this::class.simpleName
    }

    fun getPersistenceFilename(site: Site): String {
        return getRepoName(site) + ".notices.json"
    }

    override fun persistOffline(context: Context, site: Site, data: List<Notice>) {
        try {
            // Create a file in the private directory to store the data
            val file = File(context.filesDir, getPersistenceFilename(site))
            file.writeText(Json.encodeToString(data))

        } catch (e: Exception) {
            Log.e(TAG, "Persist failed for " + site.id, e)
        }

    }

    override fun fetchOffline(context: Context, site: Site): List<Notice> {
        return try {
            // Create a file reference for the stored data
            val file = File(context.filesDir, getPersistenceFilename(site))

            // Read the JSON data from the file
            val jsonString = file.readText()

            // Decode the JSON data using Kotlin Serialization
            Json.decodeFromString(jsonString)
        } catch (e: Exception) {
            Log.e(TAG, "Fetch failed for " + site.id, e)
            emptyList()
        }
    }
}