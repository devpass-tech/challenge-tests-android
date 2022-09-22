package com.devpass.spaceapp.data.api

import com.devpass.spaceapp.data.model.Launch
import com.devpass.spaceapp.data.model.Launchpad
import com.devpass.spaceapp.data.model.Rocket
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXAPIService {

    @GET("v5/launches/latest")
    fun getLatestLaunch() : Call<Launch>

    @GET("v5/launches")
    fun getAllLaunches() : Call<List<Launch>>

    @GET("v4/launchpads/{id}")
    fun getLaunchpad(@Path("id") id: String) : Call<Launchpad>

    @GET("v4/rockets/{id}")
    fun getRocket(@Path("id") id: String) : Call<Rocket>
}