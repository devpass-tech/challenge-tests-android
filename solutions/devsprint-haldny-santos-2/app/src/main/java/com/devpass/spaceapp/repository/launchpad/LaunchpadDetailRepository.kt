package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.tests.utils.NetworkResult

interface LaunchpadDetailRepository {
    suspend fun fetchLaunchpad(id: String) : NetworkResult<LaunchpadDetail>
}