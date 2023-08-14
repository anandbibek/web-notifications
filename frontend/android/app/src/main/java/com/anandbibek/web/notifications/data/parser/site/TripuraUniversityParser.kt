package com.anandbibek.web.notifications.data.parser.site

import com.anandbibek.web.notifications.data.parser.BaseParser
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import org.jsoup.nodes.Document

class TripuraUniversityParser : BaseParser() {

    override fun process(doc: Document, page: Page): List<Notice> {
        // Select the <table> element with id "TblNotification"
        val tableRows = doc.select(page.parseTree)

        // Extract data and create Notice objects
        var index = 0;
        val notices = tableRows.map { row ->
            val columns = row.select("td")
            val text = columns[1].text()
            val linkElement = columns[2].selectFirst("a")
            val link = linkElement?.absUrl("href") ?: ""
            val data = link.substringAfterLast("/")
            val time = System.currentTimeMillis();
            Notice(index++, text, data, link, time, page.name)

        }
        return notices
    }
}