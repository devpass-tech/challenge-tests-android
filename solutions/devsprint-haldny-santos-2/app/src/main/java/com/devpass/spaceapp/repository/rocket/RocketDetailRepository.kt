package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.tests.utils.NetworkResult

interface RocketDetailRepository {
    suspend fun fetchRocketDetail(id: String): NetworkResult<RocketDetail>
}
