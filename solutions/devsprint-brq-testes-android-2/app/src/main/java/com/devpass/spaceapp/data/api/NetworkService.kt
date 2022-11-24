package com.devpass.spaceapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private fun initRetrofit(url: String) =
        Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getSpaceXAPI(url: String): SpaceXAPIService =
        initRetrofit(url).create(SpaceXAPIService::class.java)
}