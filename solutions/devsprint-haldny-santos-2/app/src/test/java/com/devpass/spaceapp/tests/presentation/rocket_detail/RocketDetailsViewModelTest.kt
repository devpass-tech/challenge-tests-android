package com.devpass.spaceapp.tests.presentation.rocket_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.fakeRocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.tests.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RocketDetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val rocketDetailRepository = mockk<RocketDetailRepository>(relaxed = true)

    private val viewModel by lazy {
        RocketDetailsViewModel(rocketDetailRepository)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
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

        // THEN (Assertion)
        coVerify { rocketDetailRepository.fetchRocketDetail(any()) }
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN onSuccess Result with NetworkResult Error WHEN loadRocketDetails is called THEN set RocketDetailsUiState Error with Exception to LiveData`() {
        // GIVEN
        val fakeException = Exception("Error!")

        val expected = RocketDetailsUiState.Error(fakeException)
        coEvery {
            rocketDetailRepository.fetchRocketDetail(any())
        } returns NetworkResult.Error(fakeException)

        // WHEN
        runTest {
            viewModel.loadRocketDetails(ANY_ID)
        }

        // THEN (Assertion)
        coVerify { rocketDetailRepository.fetchRocketDetail(any()) }
        assertEquals(expected, viewModel.uiState.value)
    }

    @Test
    fun `GIVEN onFailure Result WHEN loadRocketDetails is called THEN set RocketDetailsUiState Error with Exception to LiveData`() {
            // GIVEN
            val fakeException = Exception("Error!")

            val expected = RocketDetailsUiState.Error(fakeException)
            coEvery { rocketDetailRepository.fetchRocketDetail(any()) } throws fakeException

            // WHEN
            runTest {
                viewModel.loadRocketDetails(ANY_ID)
            }

            // THEN (Assertion)
            coVerify { rocketDetailRepository.fetchRocketDetail(any()) }
            assertEquals(expected, viewModel.uiState.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object {
        const val ANY_ID = "_anyId"
    }
}
