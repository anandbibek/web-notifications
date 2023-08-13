package com.anandbibek.web.notifications.data.parser

import com.anandbibek.web.notifications.model.Notice
import org.jsoup.nodes.Document

class NoParser : BaseParser() {

    override fun process(doc: Document): List<Notice> {
        return emptyList()
    }
}