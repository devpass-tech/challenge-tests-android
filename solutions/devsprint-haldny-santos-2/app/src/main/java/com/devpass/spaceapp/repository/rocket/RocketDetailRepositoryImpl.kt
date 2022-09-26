package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.utils.NetworkResult

class RocketDetailRepositoryImpl(
    val api: SpaceXAPIService,
    val mapper: RocketDetailMapper
) : RocketDetailRepository {
    override suspend fun fetchRocketDetail(id: String): NetworkResult<RocketDetail> {
        return try {
            val response = api.fetchRocketDetails(id)
            val rocketDetail = mapper.transformToRocketModel(response)
            NetworkResult.Success(data = rocketDetail)
        } catch (e: Exception) {
            NetworkResult.Error<Nothing>(exception = e)
            throw RuntimeException(e)
        }
    }
}
