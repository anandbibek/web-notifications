package com.anandbibek.web.notifications.data.notices

import android.content.Context
import android.util.Log
import com.anandbibek.web.notifications.model.Notice
import java.io.File
import java.io.ObjectOutputStream


interface NoticesRepository {

    companion object {
        private val TAG = this::class.simpleName
    }

    /* fetch list of notices */
    fun fetch(): List<Notice>
    fun getRepoName(): String

    fun getPersistenceFilename(): String {
        return getRepoName() + ".notices"
    }

    fun persist(context: Context, data: List<Notice>) {
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

    fun get(context: Context): List<Notice> {
        /*return fetch() { webNotices ->
            persist(context, webNotices) // Persist the data
            webNotices // Emit the data downstream
        }*/

        val notices = fetch()
        persist(context, notices)
        return  notices
    }
}