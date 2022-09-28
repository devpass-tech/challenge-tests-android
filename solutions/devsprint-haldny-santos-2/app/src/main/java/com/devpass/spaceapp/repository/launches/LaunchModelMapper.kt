package com.devpass.spaceapp.repository.launches

import android.os.Build
import androidx.annotation.RequiresApi
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.model.Launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.RuntimeException

interface LaunchModelMapper {
    fun transformToLaunchModel(launchesResponse: LaunchesResponse): Launch
}

class LaunchModelMapperImpl : LaunchModelMapper {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun transformToLaunchModel(launchesResponse: LaunchesResponse): Launch {
        val date = getFormattedDate(launchesResponse.date)

        return Launch(
            launchesResponse.nameRocket,
            launchesResponse.flightNumber.toString(),
            date,
            if (launchesResponse.status) "Success" else "Fail",
            launchesResponse.links.patch.small,
            launchesResponse.rocketId,
            launchesResponse.details ?: "Empty",
            launchesResponse.launchpadId
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFormattedDate(
        dateValue: String
    ): String {
        return try {
            val timestamp = Instant.parse(dateValue)
            val date = Date.from(timestamp)
            val sdf = SimpleDateFormat("MMMM dd, yyyy")
            sdf.format(date)
        } catch (e: RuntimeException) {
            "Bad formatted date"
        }
    }
}
