package com.anandbibek.web.notifications.data.parser

import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import org.jsoup.nodes.Document

class NoParser : BaseParser() {

    override fun process(doc: Document, page: Page): List<Notice> {
        return emptyList()
    }
}