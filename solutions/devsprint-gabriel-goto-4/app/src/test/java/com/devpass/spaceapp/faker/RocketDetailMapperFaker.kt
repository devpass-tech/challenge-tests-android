package com.devpass.spaceapp.faker

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailMapper

class RocketDetailMapperFaker : RocketDetailMapper {
    override fun transformToRocketModel(rocketDetailResponse: RocketDetailResponse): RocketDetail {
        return RocketDetail(
            id = "1",
            name = "Rocket",
            description = "Its a rocket",
            image = "link 1"
        )
    }
}
