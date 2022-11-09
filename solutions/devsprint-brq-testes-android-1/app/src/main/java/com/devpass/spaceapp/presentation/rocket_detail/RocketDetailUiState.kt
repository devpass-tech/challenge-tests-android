package com.devpass.spaceapp.presentation.rocket_detail

import com.devpass.spaceapp.model.RocketDetail

internal sealed interface RocketDetailsUiState {
    data class Success(val data: RocketDetail?) : RocketDetailsUiState
    data class Error(val exception: Throwable) : RocketDetailsUiState
    object Loading : RocketDetailsUiState
}