package com.anandbibek.web.notifications.ui.homeview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.anandbibek.web.notifications.data.sites.SitesRepository
import com.anandbibek.web.notifications.model.ErrorMessage
import com.anandbibek.web.notifications.model.Site
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI state for the Home route.
 *
 * This is derived from [HomeViewModelState], but split into possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoSites(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState

    data class StaticSites(
        val siteList: List<Site>,
        val selectedSite: Site?,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState
}

/**
 * An internal representation of the Home route state, in a raw form
 */
private data class HomeViewModelState(
    val siteList: List<Site>? = null,
    val preSelectedSiteId: String?,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = ""
) {

    /**
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */
    fun toUiState(): HomeUiState =
        if (siteList == null) {
            HomeUiState.NoSites(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            HomeUiState.StaticSites(
                siteList = siteList,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                selectedSite = siteList.find {
                    it.id == preSelectedSiteId
                }
            )
        }
}


/**
 * ViewModel that handles the business logic of the Home screen
 */
class HomeViewModel private constructor(
    private val sitesRepository: SitesRepository?,
    preSelectedSiteId: String?
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
            preSelectedSiteId = preSelectedSiteId
        )
    )

    // UI state exposed to the UI
    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshSites()
    }

    /**
     * Refresh sites and update the UI state accordingly
     */
    fun refreshSites() {
        // Ui state is refreshing
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = sitesRepository?.getSites()
            viewModelState.update {
                it.copy(siteList = result, isLoading = false)
            }
        }
    }

    /**
     * Notify that an error was displayed on the screen
     */
    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    /**
     * Notify that the user updated the search query
     */
    fun onSearchInputChanged(searchInput: String) {
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }

    // factory for view model
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
