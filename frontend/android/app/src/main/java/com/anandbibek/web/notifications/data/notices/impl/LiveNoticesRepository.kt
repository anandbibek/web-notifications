package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import java.net.URL
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class LiveNoticesRepository : NoticesRepository {

    override suspend fun fetchOnline(site: Site): List<Notice> = runBlocking(Dispatchers.IO) {
        val doc = Jsoup.connect(site.url)
            .userAgent("Mozilla")
            .ignoreHttpErrors(true)
            .timeout(5000)
            .sslSocketFactory(socketFactory())
            .get();

        // Select the <ul> element with id "whats-new"
        val ulElement = doc.selectFirst("ul#whats-new")
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
            Notice(index++, text, data, URL(link), time)
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

    private fun socketFactory(): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return emptyArray()
            }

            override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
            override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
        })
        return try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            sslContext.socketFactory
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to create a SSL socket factory", e)
        } catch (e: KeyManagementException) {
            throw RuntimeException("Failed to create a SSL socket factory", e)
        }
    }
}



