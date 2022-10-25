package com.devpass.spaceapp.di

import com.devpass.spaceapp.data.api.NetworkService
import org.koin.dsl.module

val networkModule = module {
    single { NetworkService.getSpaceXAPI() }
}
