package com.devpass.spaceapp.presentation.rocket_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devpass.spaceapp.faker.FakeRocketDetailsViewModel.fakeRocketDetail
import com.devpass.spaceapp.presentation.launch_list.LaunchListViewModel
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.rules.MainDispatcherRule
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RocketDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val rocketDetailRepository = mockk<RocketDetailRepository>(relaxed = true)

    val error = Exception()

    private val viewModel by lazy {
        RocketDetailsViewModel(rocketDetailRepository)
    }

    val observer = mockk<Observer<in RocketDetailsUiState>>()

    @Before
    fun setup() {
        every { observer.onChanged(any()) } just runs
        coEvery { rocketDetailRepository.fetchRocketDetail(ANY_ID) } returns NetworkResult.Error(
            error
        )
        viewModel.uiState.observeForever(observer)
    }

    @Test
    fun `GIVEN onSuccess Result with Network Success WHEN loadRocketDetails is called THEN set RocketDetailsUiState Success with LaunchDetail to LiveData`() {
        // GIVEN
        val expected = RocketDetailsUiState.Success(fakeRocketDetail)
        coEvery {
            rocketDetailRepository.fetchRocketDetail(any())
        } returns NetworkResult.Success(fakeRocketDetail)

        // WHEN
        runTest {
            viewModel.loadRocketDetails(ANY_ID)
        }

        verifySequence {
            observer.onChanged(RocketDetailsUiState.Loading)
            observer.onChanged(expected)
        }
    }

    @Test
    fun `GIVEN onSuccess Result with NetworkResult Error WHEN loadRocketDetails is called THEN set RocketDetailsUiState Error with Exception to LiveData`() {
        // GIVEN
        val expected = RocketDetailsUiState.Error(error)
        coEvery {
            rocketDetailRepository.fetchRocketDetail(any())
        } returns NetworkResult.Error(error)

        // WHEN
        runTest {
            viewModel.loadRocketDetails(ANY_ID)
        }

        // THEN (Assertion)
        verifySequence {
            observer.onChanged(RocketDetailsUiState.Loading)
            observer.onChanged(expected)

        }
    }

    @Test
    fun `GIVEN onFailure Result WHEN loadRocketDetails is called THEN set RocketDetailsUiState Error with Exception to LiveData`() {
        // GIVEN
        val expected = RocketDetailsUiState.Error(error)
        coEvery { rocketDetailRepository.fetchRocketDetail(any()) } throws error

        // WHEN
        runTest {

            viewModel.loadRocketDetails(ANY_ID)
        }

        verifySequence {
            observer.onChanged(RocketDetailsUiState.Loading)
            observer.onChanged(expected)
        }
    }

    private companion object {
        const val ANY_ID = "_anyId"
    }
}