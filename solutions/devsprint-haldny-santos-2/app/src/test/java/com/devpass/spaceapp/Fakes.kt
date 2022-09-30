package com.devpass.spaceapp

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.OptionsRequest
import com.devpass.spaceapp.data.api.response.QueryParams
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.data.api.response.LaunchesPageResponse
import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.model.RocketDetail

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

val fakeLaunchpadDetailResponse = LaunchpadDetailResponse(
    id = "id",
    name = "VAFB SLC 4E",
    locality = "Vandenberg Air Force Base",
    region = "California",
    latitude = 34.74203,
    longitude = -120.57244,
    launchAttempts = 15,
    launchSuccesses = 15
)

val fakeLaunchpadDetail = LaunchpadDetail(
    id = "id",
    name = "VAFB SLC 4E",
    locality = "Vandenberg Air Force Base",
    region = "California",
    latitude = 34.74203,
    longitude = -120.57244,
    launchAttempts = 15,
    launchSuccesses = 15
)
val fakeRocketDetailResponse = RocketDetailResponse(
    id = "5e9d0d95eda69955f709d1eb",
    name = "Falcon 1",
    description = "The Falcon 1 was an expendable launch system privately developed and manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became the first privately-developed liquid-fuel launch vehicle to go into orbit around the Earth.",
    flickrImages = listOf("https://imgur.com/DaCfMsj.jpg", "https://imgur.com/azYafd8.jpg")
)

val fakeRocketDetail = RocketDetail(
    id = "5e9d0d95eda69955f709d1eb",
    name = "Falcon 1",
    description = "The Falcon 1 was an expendable launch system privately developed and manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became the first privately-developed liquid-fuel launch vehicle to go into orbit around the Earth.",
    image = "https://imgur.com/DaCfMsj.jpg"
)
