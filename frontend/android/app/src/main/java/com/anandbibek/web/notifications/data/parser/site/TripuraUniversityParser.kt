package com.anandbibek.web.notifications.data.parser.site

import com.anandbibek.web.notifications.data.parser.ParserBase
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import org.jsoup.nodes.Document

class TripuraUniversityParser : ParserBase() {

    override fun process(doc: Document, page: Page): List<Notice> {
        val tableRows = doc.select(page.parseTree)
        var index = 0;
        val notices = tableRows.map { row ->
            val text = processText(row.select("td")[1])
            val link = processLink(row.select("td")[2])
            val data = link.substringAfterLast("/")
            Notice(index++, text, data, link, page.name)

        }
        return notices
    }
}