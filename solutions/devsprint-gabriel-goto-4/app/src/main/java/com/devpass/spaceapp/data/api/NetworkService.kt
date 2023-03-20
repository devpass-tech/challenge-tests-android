package com.devpass.spaceapp.data.api

import com.devpass.spaceapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getSpaceXAPI() = retrofit.create(SpaceXAPIService::class.java)
}