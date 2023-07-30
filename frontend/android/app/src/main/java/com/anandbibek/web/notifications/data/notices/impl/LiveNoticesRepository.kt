package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.net.URL

class LiveNoticesRepository : NoticesRepository {

    override suspend fun fetchOnline(site: Site): List<Notice> = runBlocking(Dispatchers.IO) {
        val doc = Jsoup.connect(site.url)
            .userAgent("Mozilla")
            .timeout(5000)
            .get();

        // Select the <ul> element with id "whats-new"
        val ulElement = doc.selectFirst("ul#whats-new")
        // Select all <li> elements within the <ul> with id "whats-new"
        val liElements = ulElement?.select("li")

        // Extract data and create Notice objects
        val notices = liElements?.map { li ->
            val linkElement = li.selectFirst("a")
            val link = linkElement?.absUrl("href") ?: ""
            val text = linkElement?.text()?.trim() ?: ""
            val time = li.selectFirst("img")?.parent()?.ownText()?.trim() ?: ""
            Notice(text, "Data placeholder", URL(link), time)
        }

        notices ?: emptyList()

        /*listOf(
            Notice(
                data = "data",
                isStarred = false,
                time = "today",
                title = "A big notice",
                url
                = URL("https://google.com")
            )
        )*/
    }

    override fun getRepoName(site: Site): String {
        return site.id;
    }
}



