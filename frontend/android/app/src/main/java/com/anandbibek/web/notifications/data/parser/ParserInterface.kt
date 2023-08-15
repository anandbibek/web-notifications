package com.anandbibek.web.notifications.data.parser

import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import org.jsoup.nodes.Document

interface ParserInterface {
    suspend fun fetchOnline(page: Page): List<Notice>
    fun process(doc: Document, page: Page): List<Notice>
}