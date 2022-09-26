package com.devpass.spaceapp.repository

import com.devpass.spaceapp.presentation.launchList.LaunchModel
import com.devpass.spaceapp.utils.NetworkResult

interface FetchLaunchesRepository {
    suspend fun fetchLaunches(): NetworkResult<List<LaunchModel>>
}
