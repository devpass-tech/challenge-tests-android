package com.devpass.spaceapp.data.model

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("id")
    var id : String,

    @SerializedName("name")
    var name : String,

    @SerializedName("description")
    var description : String,

    @SerializedName("wikipedia")
    var wikipedia : String,

    @SerializedName("company")
    var company : String,

    @SerializedName("country")
    var country : String,

    @SerializedName("success_rate_pct")
    var successRate : Double,

    @SerializedName("active")
    var active : Boolean,
)