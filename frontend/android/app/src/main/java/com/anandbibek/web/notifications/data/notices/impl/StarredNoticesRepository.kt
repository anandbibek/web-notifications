package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import kotlinx.coroutines.flow.Flow

class StarredNoticesRepository: NoticesRepository {

    override suspend fun fetch(): Flow<List<Notice>> {
        TODO("Not yet implemented")
    }

    override fun getRepoName(): String {
        TODO("Not yet implemented")
    }
}