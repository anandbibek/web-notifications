package com.anandbibek.web.notifications.data.notices.impl.site

import com.anandbibek.web.notifications.data.notices.impl.LiveNoticesRepository
import com.anandbibek.web.notifications.model.Notice
import org.jsoup.nodes.Document

class EmptyRepo : LiveNoticesRepository() {

    override fun process(doc: Document): List<Notice> {
        return emptyList()
    }
}