package com.devpass.spaceapp.data.model

import com.google.gson.annotations.SerializedName

data class Launchpad(
    @SerializedName("id")
    var id : String,

    @SerializedName("name")
    var name : String,

    @SerializedName("full_name")
    var fullName : String,

    @SerializedName("locality")
    var locality : String,

    @SerializedName("region")
    var region : String,

    @SerializedName("latitude")
    var latitude : Double,

    @SerializedName("longitude")
    var longitude : Double,
)