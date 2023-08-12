package com.anandbibek.web.notifications.data.notices

import android.content.Context
import android.util.Log
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
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

    fun persistOffline(context: Context, site: Site, data: List<Notice>) {
        try {
            // Create a file in the private directory to store the data
            val file = File(context.filesDir, getPersistenceFilename(site))
            file.writeText(Json.encodeToString(data))

        } catch (e: Exception) {
            Log.e(TAG, "Persist failed for " + site.id, e)
        }

    }

    fun fetchOffline(context: Context, site: Site): List<Notice> {
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

    /*suspend fun get(context: Context, site: Site): List<Notice> {

        var notices = fetchOffline(context, site)
        notices = fetchOnline(site)
        persistOffline(context, site, notices)
        return notices
    }*/

    fun get(context: Context, site: Site, onLoadStart: () -> Unit, onLoadComplete: () -> Unit):
            Flow<List<Notice>> = flow {
        // Load offline data first
        val offlineNotices = fetchOffline(context, site)
        emit(offlineNotices)

        // Load online data and emit if available
        onLoadStart()
        val onlineNotices = fetchOnline(site)

        if (onlineNotices.isNotEmpty()) {
            emit(onlineNotices)
            onLoadComplete()
            persistOffline(context, site, onlineNotices)
        } else {
            onLoadComplete()
        }
    }.flowOn(Dispatchers.IO)
}