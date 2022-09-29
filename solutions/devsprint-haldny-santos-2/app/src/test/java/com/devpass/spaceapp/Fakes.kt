package com.devpass.spaceapp

import com.devpass.spaceapp.data.api.response.LaunchesPageResponse
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.OptionsRequest
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.data.api.response.QueryParams
import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.model.LaunchpadDetail


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
