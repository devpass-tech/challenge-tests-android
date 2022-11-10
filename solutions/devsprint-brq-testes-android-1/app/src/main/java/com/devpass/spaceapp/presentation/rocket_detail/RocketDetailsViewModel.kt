package com.devpass.spaceapp.presentation.rocket_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.domain.RocketDetailUseCase
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.utils.NetworkResult
import kotlinx.coroutines.launch

internal class RocketDetailsViewModel(
    private val useCase: RocketDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<RocketDetailsUiState>()
    val uiState: LiveData<RocketDetailsUiState> = _uiState

    fun loadRocketDetails(id: String) {
        viewModelScope.launch {
            _uiState.postValue(RocketDetailsUiState.Loading)

            runCatching { useCase(id) }
                .onSuccess(::onFetchRocketDetailResponse)
                .onFailure(::onFetchRocketDetailFailure)
        }
    }

    private fun onFetchRocketDetailFailure(e: Throwable) {
        _uiState.value = RocketDetailsUiState.Error(e)
    }

    private fun onFetchRocketDetailResponse(result: NetworkResult<RocketDetail>) =
        when (result) {
            is NetworkResult.Error -> {
                _uiState.value = RocketDetailsUiState.Error(result.exception)
            }
            is NetworkResult.Success -> {
                _uiState.value = RocketDetailsUiState.Success(result.data)
            }
        }
}
