package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail

interface LaunchpadDetailMapper {
    fun transformToLaunchpadModel(launchpadDetailResponse: LaunchpadDetailResponse) : LaunchpadDetail
}

class LaunchpadDetailMapperImpl : LaunchpadDetailMapper {
    override fun transformToLaunchpadModel(launchpadDetailResponse: LaunchpadDetailResponse) : LaunchpadDetail {
        return LaunchpadDetail(
            launchpadDetailResponse.id,
            launchpadDetailResponse.name,
            launchpadDetailResponse.locality,
            launchpadDetailResponse.region,
            launchpadDetailResponse.latitude,
            launchpadDetailResponse.longitude,
            launchpadDetailResponse.launchAttempts,
            launchpadDetailResponse.launchSuccesses
        )
    }
}
