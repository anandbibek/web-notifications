package com.anandbibek.web.notifications.data.parser.site

import com.anandbibek.web.notifications.data.parser.BaseParser
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import org.jsoup.nodes.Document

class TPSCParser : BaseParser() {

    override fun process(doc: Document, page: Page): List<Notice> {
        // Select the <ul> element with id "whats-new"
        val ulElement = doc.selectFirst(page.parseTree)
        // Select all <li> elements within the <ul> with id "whats-new"
        val liElements = ulElement?.select("li")

        // Extract data and create Notice objects
        var index = 0;
        val notices = liElements?.map { li ->
            val linkElement = li.selectFirst("a")
            val link = linkElement?.absUrl("href") ?: ""
            val data = link.substringAfterLast("/")
            val text = linkElement?.text()?.trim() ?: ""
            val time = System.currentTimeMillis();
            Notice(index++, text, data, link, time, page.name)
        }
        return notices ?: emptyList()
    }
}