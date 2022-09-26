package com.devpass.spaceapp.data.api.response

import com.google.gson.annotations.SerializedName

data class RocketDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("flickr_images")
    val flickrImages: List<String>
)
