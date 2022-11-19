package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

open class MockLaunchTest {

    fun mockFailLaunchModel() = Launch(
        name = "string 2",
        number = "1",
        date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
        status = "Fail",
        image = "string 1",
        rocketId = "id",
        details = "details",
        launchpadId = "launch id"
    )

    fun mockSuccessLaunchModel() = Launch(
        name = "string 2",
        number = "1",
        date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
        status = "Success",
        image = "string 1",
        rocketId = "id",
        details = "details",
        launchpadId = "launch id"
    )


    fun mockSuccessLaunchesResponse() = LaunchesResponse(
        links = mockLinks(),
        nameRocket = "string 2",
        date = "2008-08-03T03:34:00.000Z",
        status = true,
        flightNumber = 1,
        rocketId = "id",
        details = "details",
        launchpadId = "launch id"
    )

    fun mockFailLaunchesResponse() = LaunchesResponse(
        links = mockLinks(),
        nameRocket = "string 2",
        date = "2008-08-03T03:34:00.000Z",
        status = false,
        flightNumber = 1,
        rocketId = "id",
        details = "details",
        launchpadId = "launch id"
    )

    private fun mockLinks() = Links(
        Patch(
            "string 1"
        )
    )
}
