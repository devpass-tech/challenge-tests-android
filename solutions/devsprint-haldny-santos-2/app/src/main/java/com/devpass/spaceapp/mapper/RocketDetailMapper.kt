package com.devpass.spaceapp.mapper

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail

fun RocketDetailResponse.toModel(): RocketDetail {
    return RocketDetail(
        id = id,
        name = name,
        description = description,
        image = flickrImages.firstOrNull() ?: ""
    )
}
