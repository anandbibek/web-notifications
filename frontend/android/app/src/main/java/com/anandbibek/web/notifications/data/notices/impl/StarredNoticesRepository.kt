package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice

class StarredNoticesRepository: NoticesRepository {

    override fun fetch(): List<Notice> {
        TODO("Not yet implemented")
    }

    override fun getRepoName(): String {
        return "starred_notices"
    }
}