package com.devpass.spaceapp.model

data class LaunchpadDetail(
    val id: String,
    val name: String,
    val locality: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    val launchAttempts: Int,
    val launchSuccesses: Int
)
