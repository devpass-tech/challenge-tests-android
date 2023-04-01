package com.devpass.spaceapp.presentation.rocket_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RocketDetailsViewModel(
    private val rocketDetailRepository: RocketDetailRepository
) : ViewModel() {
    private val _uiState = MutableLiveData<RocketDetailsUiState>()
    val uiState: LiveData<RocketDetailsUiState> = _uiState

    init {
        RocketDetailsUiState.Loading
    }

    fun loadRocketDetails(id: String) {
        viewModelScope.launch {
            _uiState.postValue(RocketDetailsUiState.Loading)

            runCatching {
                delay(3000)
                rocketDetailRepository.fetchRocketDetail(id)
            }.onSuccess {
                if (it is NetworkResult.Success) {
                    _uiState.value = RocketDetailsUiState.Success(it.data)
                }

                if (it is NetworkResult.Error) {
                    _uiState.value = RocketDetailsUiState.Error(it.exception)
                }
            }.onFailure {
                _uiState.value = RocketDetailsUiState.Error(it)
            }
        }
    }
}

sealed interface RocketDetailsUiState {
    data class Success(val data: RocketDetail?) : RocketDetailsUiState
    data class Error(val exception: Throwable) : RocketDetailsUiState
    object Loading : RocketDetailsUiState
}
