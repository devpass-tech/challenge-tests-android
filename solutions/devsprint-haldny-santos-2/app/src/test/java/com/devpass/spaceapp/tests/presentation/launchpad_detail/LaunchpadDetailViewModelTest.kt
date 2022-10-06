package com.devpass.spaceapp.tests.presentation.launchpad_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.fakeLaunchpadDetail
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepository
import com.devpass.spaceapp.tests.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LaunchpadDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    private val repository = mockk<LaunchpadDetailRepository>(relaxed = true)
    private val viewModel by lazy {
        LaunchpadDetailViewModel(repository)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `GIVEN launchpad id valid WHEN safeLaunchpadDetailCall is called THEN return a LaunchpadDetailUIState Success with an object LaunchpadDetail`() {
        val expected = LaunchpadDetailUIState.Success(fakeLaunchpadDetail)

        coEvery {
            repository.fetchLaunchpad(launchpadId)
        } returns NetworkResult.Success(fakeLaunchpadDetail)

        runTest {
            viewModel.safeLaunchpadDetailCall(launchpadId)
        }

        coVerify {
            repository.fetchLaunchpad(launchpadId)
        }
        assertEquals(expected, viewModel.launchpadDetailUI.value)
    }

    @Test
    fun `GIVEN onSuccess with Network Error WHEN fetchLaunchpad is called THEN return a LaunchpadDetailUIState status Error`(){
        val expected = LaunchpadDetailUIState.Error(fakeException)

        coEvery {
            repository.fetchLaunchpad(launchpadId)
        } returns NetworkResult.Error(fakeException)

        runTest {
            viewModel.safeLaunchpadDetailCall(launchpadId)
        }

        coVerify {
            repository.fetchLaunchpad(launchpadId)
        }
        assertEquals(expected, viewModel.launchpadDetailUI.value)
    }


    @Test
    fun `GIVEN onFailure exception WHEN fetchLaunchpad is called THEN return a LaunchpadDetailUIState status Error with an exception`(){
        val expected = LaunchpadDetailUIState.Error(fakeException)

        coEvery {
            repository.fetchLaunchpad(launchpadId)
        } throws fakeException

        runTest {
            viewModel.safeLaunchpadDetailCall(launchpadId)
        }

        coVerify {
            repository.fetchLaunchpad(launchpadId)
        }
        assertEquals(expected, viewModel.launchpadDetailUI.value)
    }




    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private companion object{
        val launchpadId = "5e9d0d95eda69955f709d1eb"
        val fakeException = Exception("Error")
    }
}
