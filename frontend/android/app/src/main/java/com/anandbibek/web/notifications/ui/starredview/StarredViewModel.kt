package com.anandbibek.web.notifications.ui.starredview

import androidx.lifecycle.ViewModel
import com.anandbibek.web.notifications.data.notices.NoticesRepository
import com.anandbibek.web.notifications.model.Notice
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class StarredUiState(
    val notices :List<Notice> = emptyList(),
    val loading: Boolean = false,
)

@HiltViewModel
class StarredViewModel @Inject constructor(
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
