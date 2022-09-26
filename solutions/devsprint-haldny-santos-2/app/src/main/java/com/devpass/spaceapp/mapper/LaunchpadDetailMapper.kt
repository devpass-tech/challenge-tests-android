package com.devpass.spaceapp.mapper

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail

fun LaunchpadDetailResponse.toModel() : LaunchpadDetail {
    return LaunchpadDetail(
        id,
        name,
        locality,
        region,
        latitude,
        longitude,
        launchAttempts,
        launchSuccesses
    )
}
