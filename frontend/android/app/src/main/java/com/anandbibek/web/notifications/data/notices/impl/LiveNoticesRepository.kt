package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import java.net.URL

class LiveNoticesRepository : NoticesRepository {

    override fun fetch(): List<Notice> {
        return listOf(
            Notice(
                data = "data", isStarred = false, time = "today", title = "A big notice", url
                = URL("https://google.com")
            )
        )
    }

    override fun getRepoName(): String {
        TODO("Not yet implemented")
    }
}