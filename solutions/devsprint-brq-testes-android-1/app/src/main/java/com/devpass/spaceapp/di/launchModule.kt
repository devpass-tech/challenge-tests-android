package com.devpass.spaceapp.di

import com.devpass.spaceapp.presentation.launch.LaunchNavigator
import com.devpass.spaceapp.presentation.launch.LaunchNavigatorImpl
import org.koin.dsl.module

val launchModule = module {
    factory<LaunchNavigator> { LaunchNavigatorImpl() }
}