package com.devpass.spaceapp.presentation.rocket_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RocketDetailsViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val rocketDetailRepository = mockk<RocketDetailRepository>()
    private val rocketDetailsViewModel = RocketDetailsViewModel(rocketDetailRepository)

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `return success when calledFlow`(){
        val parameter = "is_success"

        val observer = mockk<Observer<in RocketDetailsUiState>>(relaxed = true)
        rocketDetailsViewModel.uiState.observeForever(observer)

        val data = mockk<RocketDetail>()
        coEvery {
            rocketDetailRepository.fetchRocketDetail(parameter)
        }returns NetworkResult.Success(data)

        rocketDetailsViewModel.loadRocketDetails(parameter)

        verifySequence {
            observer.onChanged(RocketDetailsUiState.Loading)
            observer.onChanged(RocketDetailsUiState.Success(data))
        }
    }

    @Test
    fun `return not success when calledFlow`(){
        val parameter = "not_success"
        val observer = mockk<Observer<in RocketDetailsUiState>>(relaxed = true)
        rocketDetailsViewModel.uiState.observeForever(observer)

        val exception = mockk<Throwable>()
        coEvery {
            rocketDetailRepository.fetchRocketDetail(parameter)
        }returns NetworkResult.Error(exception)

        rocketDetailsViewModel.loadRocketDetails(parameter)

        verifySequence {
            observer.onChanged(RocketDetailsUiState.Loading)
            observer.onChanged(RocketDetailsUiState.Error(exception))
        }
    }

    @Test
    fun `return onFailure when calledFlow`(){
        val parameter = "not_success"
        val observer = mockk<Observer<in RocketDetailsUiState>>(relaxed = true)
        rocketDetailsViewModel.uiState.observeForever(observer)

        val throwable = Throwable()
        coEvery {
            rocketDetailRepository.fetchRocketDetail(parameter)
        }throws throwable

        rocketDetailsViewModel.loadRocketDetails(parameter)

        verifySequence {
            observer.onChanged(RocketDetailsUiState.Loading)
            observer.onChanged(RocketDetailsUiState.Error(throwable))
        }
    }
}