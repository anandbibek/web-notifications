package com.anandbibek.web.notifications.data.parser.site

import com.anandbibek.web.notifications.data.parser.ParserBase
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import org.jsoup.nodes.Document

class TPSCParser : ParserBase() {

    override fun process(doc: Document, page: Page): List<Notice> {
        val liElements = doc.select("ul#whats-new li")
        var index = 0;
        val notices = liElements.map { row ->
            val text = processText(row.select("a")[0])
            val link = processLink(row)
            val data = link.substringAfterLast("/")
            Notice(index++, text, data, link, page.name)
        }
        return notices
    }
}