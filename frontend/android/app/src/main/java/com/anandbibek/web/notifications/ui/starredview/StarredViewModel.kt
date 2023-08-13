package com.anandbibek.web.notifications.ui.starredview

import androidx.lifecycle.ViewModel
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice

data class StarredUiState(
    val notices :List<Notice> = emptyList(),
    val loading: Boolean = false,
)

class StarredViewModel constructor(
    private val starredNoticesRepository: NoticesRepository?
) : ViewModel() {

//    TODO implement


    /*companion object {
        fun provideFactory(
            starredNoticesRepository: NoticesRepository?,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StarredViewModel(starredNoticesRepository) as T
            }
        }
    }*/

}
