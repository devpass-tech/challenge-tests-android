package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.utils.NetworkResult

interface FetchLaunchesRepository {
    suspend fun fetchLaunches(): NetworkResult<List<Launch>>
}
