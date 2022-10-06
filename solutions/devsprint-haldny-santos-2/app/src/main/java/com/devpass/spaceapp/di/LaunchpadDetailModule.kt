package com.devpass.spaceapp.di

import com.devpass.spaceapp.tests.presentation.launchpad_detail.LaunchpadDetailViewModel
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailMapper
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailMapperImpl
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepository
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val launchpadDetailModule = module {

    single<LaunchpadDetailRepository> { LaunchpadDetailRepositoryImpl(get(), get()) }
    single<LaunchpadDetailMapper> { LaunchpadDetailMapperImpl() }

    viewModel { LaunchpadDetailViewModel(get()) }

}
