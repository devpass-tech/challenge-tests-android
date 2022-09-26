package com.devpass.spaceapp.data.api.response

import com.google.gson.annotations.SerializedName

data class QueryParams(
    @SerializedName("options")
    val options: OptionsRequest,
)

data class OptionsRequest(
    @SerializedName("limit")
    val limit: Int,
)
