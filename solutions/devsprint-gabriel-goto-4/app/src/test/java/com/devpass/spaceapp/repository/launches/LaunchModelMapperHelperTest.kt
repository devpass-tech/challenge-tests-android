package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch

object LaunchModelMapperHelperTest{

    val response = LaunchesResponse(
        date = "2020-05-30T19:22:00.000Z",
        nameRocket = "Rocket",
        flightNumber = 1,
        status = true,
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        links = Links(Patch(small="small"))
    )
    val launch = Launch(
        date = "May 30, 2020",
        name = "Rocket" ,
        number = "1",
        status = "Success",
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        image = "small"
    )

    val statusSuccess = LaunchesResponse(
        date = "2020-05-30T19:22:00.000Z",
        nameRocket = "Rocket",
        flightNumber = 1,
        status = true,
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        links = Links(Patch(small="small"))
    )
    val launchStatusSuccess = Launch(
        date = "May 30, 2020",
        name = "Rocket" ,
        number = "1",
        status = "Success",
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        image = "small"
    )

    val statusFail = LaunchesResponse(
        date = "2020-05-30T19:22:00.000Z",
        nameRocket = "Rocket",
        flightNumber = 1,
        status = false,
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        links = Links(Patch(small="small"))
    )
    val launchStatusFail = Launch(
        date = "May 30, 2020",
        name = "Rocket" ,
        number = "1",
        status = "Fail",
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        image = "small"
    )

    val details = LaunchesResponse(
        date = "2020-05-30T19:22:00.000Z",
        nameRocket = "Rocket",
        flightNumber = 1,
        status = true,
        rocketId = "RocketId",
        details = "",
        launchpadId = "LaunchpadId",
        links = Links(Patch(small="small"))
    )
    val launchDetails = Launch(
        date = "May 30, 2020",
        name = "Rocket" ,
        number = "1",
        status = "Success",
        rocketId = "RocketId",
        details = "",
        launchpadId = "LaunchpadId",
        image = "small"
    )
}