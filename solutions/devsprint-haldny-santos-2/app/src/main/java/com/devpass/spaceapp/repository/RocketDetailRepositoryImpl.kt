package com.devpass.spaceapp.repository

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.mapper.toModel
import com.devpass.spaceapp.model.RocketDetail

class RocketDetailRepositoryImpl(val api: SpaceXAPIService) : RocketDetailRepository {
    override suspend fun fetchRocketDetail(id: String): RocketDetail {
        return api.fetchRocketDetails(id).toModel()
    }
}
