package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.utils.NetworkResult

class LaunchpadDetailRepositoryImpl(
    val api: SpaceXAPIService,
    val mapper: LaunchpadDetailMapper
) : LaunchpadDetailRepository {

    override suspend fun fetchLaunchpad(id: String): NetworkResult<LaunchpadDetail> {
        return try {
            val response = api.fetchLaunchpadDetails(id)
            val launchpadDetail = mapper.transformToLaunchpadModel(response)
            NetworkResult.Success(data = launchpadDetail)
        } catch (e: Exception) {
            NetworkResult.Error<Nothing>(exception = e)
            throw RuntimeException(e)
        }
    }
}
