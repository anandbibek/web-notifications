package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.data.parser.ParserFactory
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LiveNoticesRepository : NoticesRepository {

    override suspend fun fetchOnline(site: Site, parserFactory: ParserFactory): List<Notice> =
        withContext(
            Dispatchers
                .IO
        ) {

            site.pages.flatMap { page ->
                parserFactory.getParser(page).fetchOnline(page)
            }


        }

    override fun getRepoName(site: Site): String {
        return site.id;
    }
}



