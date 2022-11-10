package com.devpass.spaceapp.di

import com.devpass.spaceapp.domain.RocketDetailUseCase
import com.devpass.spaceapp.domain.RocketDetailUseCaseImpl
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsViewModel
import com.devpass.spaceapp.repository.rocket.RocketDetailMapper
import com.devpass.spaceapp.repository.rocket.RocketDetailMapperImpl
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.repository.rocket.RocketDetailRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rocketDetailModule = module {

    factory<RocketDetailUseCase> { RocketDetailUseCaseImpl(get()) }
    single<RocketDetailRepository> { RocketDetailRepositoryImpl(get(), get()) }
    single<RocketDetailMapper> { RocketDetailMapperImpl() }

    viewModel { RocketDetailsViewModel(get()) }

}
