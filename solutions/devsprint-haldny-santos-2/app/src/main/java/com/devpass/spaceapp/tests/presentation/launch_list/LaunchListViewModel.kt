package com.devpass.spaceapp.tests.presentation.launch_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.tests.utils.NetworkResult
import kotlinx.coroutines.launch

class LaunchListViewModel(
    val repository: FetchLaunchesRepository,
) : ViewModel() {

    private val _launches: MutableLiveData<LaunchListUIState> = MutableLiveData()
    val launches: LiveData<LaunchListUIState> = _launches

    init {
        getLaunches()
    }

    fun getLaunches() = viewModelScope.launch {
        safeLaunchesCall()
    }

    private suspend fun safeLaunchesCall() {
        _launches.postValue(LaunchListUIState.Loading)

        runCatching {
            repository.fetchLaunches()
        }.onSuccess {
            if (it is NetworkResult.Success) {
                _launches.postValue(LaunchListUIState.Success(it.data))
            }
            if (it is NetworkResult.Error) {
                _launches.postValue(LaunchListUIState.Error(it.exception))
            }
        }.onFailure {
            _launches.postValue(LaunchListUIState.Error(it))
        }
    }

    sealed interface LaunchListUIState {
        object Loading : LaunchListUIState
        data class Error(val error: Throwable) : LaunchListUIState
        data class Success(val data: List<Launch>) : LaunchListUIState
    }
}
