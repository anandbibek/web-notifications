package com.anandbibek.web.notifications.data.notices.impl

import android.annotation.SuppressLint
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


abstract class LiveNoticesRepository : NoticesRepository {

    override suspend fun fetchOnline(site: Site): List<Notice> = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(site.url)
            .userAgent("Mozilla")
            .ignoreHttpErrors(true)
            .timeout(15000)
            .sslSocketFactory(socketFactory())
            .get();
        process(doc);
    }

    abstract fun process(doc: Document): List<Notice>;

    override fun getRepoName(site: Site): String {
        return site.id;
    }

    @SuppressLint("TrustAllX509TrustManager", "CustomX509TrustManager")
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



