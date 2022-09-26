package com.devpass.spaceapp.repository

import com.devpass.spaceapp.data.api.NetworkService
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.mapper.toModel
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.utils.NetworkResult

class LaunchpadDetailRepositoryImpl(
    val api: SpaceXAPIService = NetworkService.getSpaceXAPI()
) : LaunchpadDetailRepository {

    override suspend fun fetchLaunchpad(id: String): NetworkResult<LaunchpadDetail> {
        return try {
            val response = api.fetchLaunchpadDetails(id)
            val launchpadDetail = response.toModel()
            NetworkResult.Success(data = launchpadDetail)
        } catch (e: Exception) {
            NetworkResult.Error<Nothing>(exception = e)
            throw RuntimeException(e)
        }
    }
}
