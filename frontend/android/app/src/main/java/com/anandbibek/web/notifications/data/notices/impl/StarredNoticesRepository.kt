package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.parser.ParserFactory
import com.anandbibek.web.notifications.domain.model.Notice
import com.anandbibek.web.notifications.domain.model.Site

class StarredNoticesRepository: FileRepository {

    override suspend fun fetchOnline(site: Site, parserFactory: ParserFactory): List<Notice> {
        TODO("Not yet implemented")
    }

    override fun getRepoName(site: Site): String {
        return "starred_notices"
    }
}