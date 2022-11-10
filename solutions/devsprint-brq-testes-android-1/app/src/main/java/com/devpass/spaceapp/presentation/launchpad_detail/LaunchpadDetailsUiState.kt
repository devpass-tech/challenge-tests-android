package com.devpass.spaceapp.presentation.launchpad_detail

import com.devpass.spaceapp.model.LaunchpadDetail

sealed interface LaunchpadDetailUIState {
    object Loading : LaunchpadDetailUIState
    data class Error(val error: Throwable) : LaunchpadDetailUIState
    data class Success(val data: LaunchpadDetail) : LaunchpadDetailUIState
}