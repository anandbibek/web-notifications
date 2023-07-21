package com.anandbibek.web.notifications.data.notices

import android.content.Context
import android.util.Log
import com.anandbibek.web.notifications.model.Notice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.io.ObjectOutputStream


interface NoticesRepository {

    companion object {
        private val TAG = this::class.simpleName
    }

    /* fetch list of notices */
    suspend fun fetch(): Flow<List<Notice>>
    fun getRepoName(): String

    fun getPersistenceFilename(): String {
        return getRepoName() + ".notices"
    }

    suspend fun persist(context: Context, data: List<Notice>) {
        withContext(Dispatchers.IO) {
            try {
                // Create a file in the private directory to store the data
                val file = File(context.filesDir, getPersistenceFilename())

                // Write the data to the file using ObjectOutputStream
                ObjectOutputStream(file.outputStream()).use { outputStream ->
                    outputStream.writeObject(data)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Persist failed for " + getRepoName(), e)
            }
        }
    }

    suspend fun get(context: Context): Flow<List<Notice>> {
        return fetch().map { webNotices ->
            persist(context, webNotices) // Persist the data
            webNotices // Emit the data downstream
        }
    }
}