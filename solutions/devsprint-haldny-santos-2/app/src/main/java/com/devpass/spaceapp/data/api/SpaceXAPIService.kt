package com.devpass.spaceapp.data.api

import com.devpass.spaceapp.data.api.response.LaunchesPageResponse
import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.data.api.response.QueryParams
import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpaceXAPIService {

    @POST("v5/launches/query")
    suspend fun fetchNextLaunches(@Body params: QueryParams): LaunchesPageResponse

    @GET("v4/rockets/{id}")
    suspend fun fetchRocketDetails(@Path("id") id: String): RocketDetailResponse

    @GET("v4/launchpads/{id}")
    suspend fun fetchLaunchpadDetails(@Path("id") id: String): LaunchpadDetailResponse

}