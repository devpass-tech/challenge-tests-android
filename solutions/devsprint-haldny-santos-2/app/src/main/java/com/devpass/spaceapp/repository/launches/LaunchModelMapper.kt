package com.devpass.spaceapp.repository.launches

import android.os.Build
import androidx.annotation.RequiresApi
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.model.Launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

interface LaunchModelMapper {
    fun transformToLaunchModel(launchesResponse: LaunchesResponse): Launch
}

class LaunchModelMapperImpl : LaunchModelMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun transformToLaunchModel(launchesResponse: LaunchesResponse): Launch {

        val timestamp: Instant

        try {
            timestamp = Instant.parse(launchesResponse.date)
        } catch (e: Exception) {
            throw Exception("Error converting date to timestamp")
        }

        val date = Date.from(timestamp)
        val sdf = SimpleDateFormat("MMMM dd, yyyy")


        return Launch(
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
