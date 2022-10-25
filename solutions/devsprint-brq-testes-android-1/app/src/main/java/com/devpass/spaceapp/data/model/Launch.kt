package com.devpass.spaceapp.data.model

import com.google.gson.annotations.SerializedName

data class Launch(
    @SerializedName("id")
    var id : String,

    @SerializedName("name")
    var name : String,

    @SerializedName("date_utc")
    var dateUTC : String,

    @SerializedName("flight_number")
    var flightNumber : Int,

    @SerializedName("launchpad")
    var launchpad : String,

    @SerializedName("rocket")
    var rocket : String
)