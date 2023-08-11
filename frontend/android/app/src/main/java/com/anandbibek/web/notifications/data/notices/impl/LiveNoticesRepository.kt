package com.anandbibek.web.notifications.data.notices.impl

import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class LiveNoticesRepository : NoticesRepository {

    override suspend fun fetchOnline(site: Site): List<Notice> = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(site.url)
            .userAgent("Mozilla")
            .ignoreHttpErrors(true)
            .timeout(15000)
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
            Notice(index++, text, data, link, time)
        }

        notices ?: emptyList()
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



