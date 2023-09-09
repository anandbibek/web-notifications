package com.anandbibek.web.notifications.data.parser

import android.annotation.SuppressLint
import com.anandbibek.web.notifications.model.Notice
import com.anandbibek.web.notifications.model.Page
import com.anandbibek.web.notifications.model.ParsLane
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

abstract class ParserBase : ParserInterface{

    override suspend fun fetchOnline(page: Page): List<Notice> = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(page.url)
            .userAgent("Mozilla")
            .ignoreHttpErrors(true)
            .timeout(15000)
            .sslSocketFactory(socketFactory())
            .get();
        process(doc, page);
    }

    abstract override fun process(doc: Document, page: Page): List<Notice>;

    protected fun processText(element: Element?, parsLanes: List<ParsLane>): String {
        val newElement = element?.selectFirst(
            parsLanes.first {
                it.metaKey.contentEquals("text")
            }.selector
        )
        return newElement?.text()?.trim() ?: "";
    }

    protected fun processLink(element: Element?, parsLanes: List<ParsLane>): String {
        val newElement = parsLanes.firstOrNull {
            it.metaKey.contentEquals("link")
        }?.selector?.let {
            element?.selectFirst(
                it
            )
        }
        return newElement?.select("a")?.get(0)?.absUrl("href") ?: ""
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