package com.devpass.spaceapp.di

import com.devpass.spaceapp.presentation.launch_list.LaunchListViewModel
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.repository.launches.LaunchModelMapper
import com.devpass.spaceapp.repository.launches.LaunchModelMapperImpl
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val launchListModule = module {

    single<FetchLaunchesRepository> { FetchLaunchesRepositoryImpl(get(), get()) }
    single<LaunchModelMapper> { LaunchModelMapperImpl() }

    viewModel { LaunchListViewModel(get(), Dispatchers.IO) }

}
