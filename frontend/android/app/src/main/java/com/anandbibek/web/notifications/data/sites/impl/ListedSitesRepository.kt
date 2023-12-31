package com.anandbibek.web.notifications.data.sites.impl

import com.anandbibek.web.notifications.data.sites.SitesRepository
import com.anandbibek.web.notifications.data.sites.siteList
import com.anandbibek.web.notifications.domain.model.Site
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListedSitesRepository : SitesRepository {
    override suspend fun getSites(): List<Site> {
        return withContext(Dispatchers.IO) {
            siteList
        }
    }
}