package com.devpass.spaceapp

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail

object FakeLaunchpadDetail {

    val fakeLaunchpadDetailResponse = LaunchpadDetailResponse(
        id = "id",
        name = "VAFB SLC 4E",
        locality = "Vandenberg Air Force Base",
        region = "California",
        latitude = 34.74203,
        longitude = -120.57244,
        launchAttempts = 15,
        launchSuccesses = 15
    )

    val fakeLaunchpadDetail = LaunchpadDetail(
        id = "id",
        name = "VAFB SLC 4E",
        locality = "Vandenberg Air Force Base",
        region = "California",
        latitude = 34.74203,
        longitude = -120.57244,
        launchAttempts = 15,
        launchSuccesses = 15
    )
}

