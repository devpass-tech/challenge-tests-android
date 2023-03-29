package com.devpass.spaceapp.faker

import com.devpass.spaceapp.data.api.response.*
import com.devpass.spaceapp.model.Launch

class FetchLauchesFake {

    object FakeFetchLaunches {

        val fakeQueryParams = QueryParams(OptionsRequest(20))

        val fakeLaunchesReponse = LaunchesResponse(
            links = Links(Patch("")),
            nameRocket = "",
            date = "",
            status = true,
            flightNumber = 0,
            rocketId = "0",
            details = null,
            launchpadId = ""
        )

        val fakeLaunchesPageResponse = LaunchesPageResponse(
            docs = listOf(fakeLaunchesReponse),
            hasNextPage = true,
            hasPrevPage = true,
            limit = 2,
            nextPage = 0,
            offset = 1,
            page = 1,
            pagingCounter = 1,
            prevPage = 0,
            totalDocs = 2,
            totalPages = 1,
        )

        val fakeLaunch = Launch(
            name = "",
            number = "",
            date = "",
            status = "",
            image = "",
            rocketId = "",
            details = "",
            launchpadId = ""
        )
    }
}