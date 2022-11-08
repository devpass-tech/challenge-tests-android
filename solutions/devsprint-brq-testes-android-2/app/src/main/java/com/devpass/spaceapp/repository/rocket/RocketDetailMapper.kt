package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail

interface RocketDetailMapper {
    fun transformToRocketModel(rocketDetailResponse: RocketDetailResponse) : RocketDetail
}

class RocketDetailMapperImpl : RocketDetailMapper {
    override fun transformToRocketModel(rocketDetailResponse: RocketDetailResponse) : RocketDetail {
        return RocketDetail(
            id = rocketDetailResponse.id,
            name = rocketDetailResponse.name,
            description = rocketDetailResponse.description,
            image = rocketDetailResponse.flickrImages.firstOrNull() ?: ""
        )
    }
}
