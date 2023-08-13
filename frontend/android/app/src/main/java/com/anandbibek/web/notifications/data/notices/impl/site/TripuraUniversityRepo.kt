package com.anandbibek.web.notifications.data.notices.impl.site

import com.anandbibek.web.notifications.data.notices.impl.LiveNoticesRepository
import com.anandbibek.web.notifications.model.Notice
import org.jsoup.nodes.Document

class TripuraUniversityRepo : LiveNoticesRepository() {

    override fun process(doc: Document): List<Notice> {
        // Select the <table> element with id "TblNotification"
        val tableRows = doc.select("table#TblNotification tbody tr")

        // Extract data and create Notice objects
        var index = 0;
        val notices = tableRows.map { row ->
            val columns = row.select("td")
            val text = columns[1].text()
            val linkElement = columns[2].selectFirst("a")
            val link = linkElement?.absUrl("href") ?: ""
            val data = link.substringAfterLast("/")
            val time = System.currentTimeMillis();
            Notice(index++, text, data, link, time)

        }
        return notices ?: emptyList()
    }
}