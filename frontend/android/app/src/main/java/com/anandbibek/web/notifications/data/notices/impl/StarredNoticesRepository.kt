package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site

class StarredNoticesRepository: NoticesRepository {

    override suspend fun fetchOnline(site: Site): List<Notice> {
        TODO("Not yet implemented")
    }

    override fun getRepoName(site: Site): String {
        return "starred_notices"
    }
}