package com.devpass.spaceapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.RocketDetailRepository
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
            runCatching {
                delay(3000)
                rocketDetailRepository.fetchRocketDetail(id)
            }.onSuccess {
                _uiState.value = RocketDetailsUiState.Success(it)
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
