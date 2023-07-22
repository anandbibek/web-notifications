package com.anandbibek.web.notifications.ui.homeview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anandbibek.web.notifications.data.sites.SitesRepository
import com.anandbibek.web.notifications.model.Site

data class HomeUiState(
    val sites : List<Site> = emptyList(),
    val loading: Boolean = false,
)
class HomeViewModel private constructor(
    private val sitesRepository: SitesRepository?,
    preSelectedSiteId: String?
) : ViewModel() {
    //TODO

    companion object {
        fun provideFactory(
            sitesRepository: SitesRepository?,
            preSelectedSiteId: String? = null
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(sitesRepository, preSelectedSiteId) as T
            }
        }
    }
}
