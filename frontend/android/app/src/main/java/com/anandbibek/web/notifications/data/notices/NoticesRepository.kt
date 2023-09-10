package com.anandbibek.web.notifications.data.notices

import android.content.Context
import com.anandbibek.web.notifications.data.parser.ParserFactory
import com.anandbibek.web.notifications.domain.model.Notice
import com.anandbibek.web.notifications.domain.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


interface NoticesRepository {

    /* fetch list of notices */
    suspend fun fetchOnline(site: Site, parserFactory: ParserFactory): List<Notice>
    fun getRepoName(site: Site): String

    fun persistOffline(context: Context, site: Site, data: List<Notice>)

    fun fetchOffline(context: Context, site: Site): List<Notice>

    fun get(
        context: Context,
        site: Site,
        parserFactory: ParserFactory,
        onLoadStart: () -> Unit,
        onLoadComplete: () -> Unit
    ):
            Flow<List<Notice>> = flow {
        // Load offline data first
        val offlineNotices = fetchOffline(context, site)
        emit(offlineNotices)

        // Load online data and emit if available
        onLoadStart()
        val onlineNotices = fetchOnline(site, parserFactory)

        if (onlineNotices.isNotEmpty()) {
            emit(onlineNotices)
            onLoadComplete()
            persistOffline(context, site, onlineNotices)
        } else {
            onLoadComplete()
        }
    }.flowOn(Dispatchers.IO)
}