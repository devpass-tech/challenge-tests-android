package com.devpass.spaceapp.presentation.launchpad_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devpass.spaceapp.domain.LaunchpadDetailUseCase
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.utils.NetworkResult

internal class LaunchpadDetailViewModel(
    private val useCase: LaunchpadDetailUseCase
) : ViewModel() {

    private val _uiState: MutableLiveData<LaunchpadDetailUIState> = MutableLiveData()
    val uiState: LiveData<LaunchpadDetailUIState> = _uiState

    suspend fun safeLaunchpadDetailCall(id: String) {
        _uiState.postValue(LaunchpadDetailUIState.Loading)

        runCatching { useCase(id) }
            .onSuccess(::onFetchLaunchpadDetailSuccess)
            .onFailure(::onFetchLaunchpadDetailFailure)
    }

    private fun onFetchLaunchpadDetailFailure(e: Throwable) {
        _uiState.value = LaunchpadDetailUIState.Error(e)
    }

    private fun onFetchLaunchpadDetailSuccess(result: NetworkResult<LaunchpadDetail>) {
        when (result) {
            is NetworkResult.Error -> {
                _uiState.value = LaunchpadDetailUIState.Error(result.exception)
            }
            is NetworkResult.Success -> {
                _uiState.value = LaunchpadDetailUIState.Success(result.data)
            }
        }
    }
}