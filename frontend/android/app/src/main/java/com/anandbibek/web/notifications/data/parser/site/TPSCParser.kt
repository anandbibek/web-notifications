package com.anandbibek.web.notifications.data.parser.site

import com.anandbibek.web.notifications.data.parser.ParserBase
import com.anandbibek.web.notifications.domain.model.Notice
import com.anandbibek.web.notifications.domain.model.Page
import org.jsoup.nodes.Document

class TPSCParser : ParserBase() {

    override fun process(doc: Document, page: Page): List<Notice> {
        val liElements = doc.select(page.parsLane.selector)
        var index = 0;
        val notices = liElements.map { row ->
            val text = processText(row, page.parsLane.parsLanes)
            val link = processLink(row, page.parsLane.parsLanes)
            val data = link.substringAfterLast("/")
            Notice(index++, text, data, link, page.name)
        }
        return notices
    }
}