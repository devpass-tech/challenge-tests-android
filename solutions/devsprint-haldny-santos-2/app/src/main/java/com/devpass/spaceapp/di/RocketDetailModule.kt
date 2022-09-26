package com.devpass.spaceapp.di

import com.devpass.spaceapp.presentation.RocketDetailsViewModel
import com.devpass.spaceapp.repository.RocketDetailRepository
import com.devpass.spaceapp.repository.RocketDetailRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rocketDetailModule = module {

    single<RocketDetailRepository> { RocketDetailRepositoryImpl(get()) }

    viewModel { RocketDetailsViewModel(get()) }

}
