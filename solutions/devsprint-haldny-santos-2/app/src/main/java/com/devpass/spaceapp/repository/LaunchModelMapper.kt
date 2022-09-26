package com.devpass.spaceapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.presentation.launchList.LaunchModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

interface LaunchModelMapper {
    fun transformToLaunchModel(launchesResponse: LaunchesResponse) : LaunchModel
}

class LaunchModelMapperImpl : LaunchModelMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun transformToLaunchModel(launchesResponse: LaunchesResponse) : LaunchModel {

        val timestamp = Instant.parse(launchesResponse.date)
        val date = Date.from(timestamp)
        val sdf = SimpleDateFormat("MMMM dd, yyyy")

        return LaunchModel(
            launchesResponse.nameRocket,
            launchesResponse.flightNumber.toString(),
            sdf.format(date),
            if (launchesResponse.status) "Success" else "Fail",
            launchesResponse.links.patch.small,
            launchesResponse.rocketId,
            launchesResponse.details ?: "Empty",
            launchesResponse.launchpadId
        )
    }
}
