package com.devpass.spaceapp.presentation.launch_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.fakeLaunch
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.presentation.launch_list.LaunchListViewModel.LaunchListUIState
import com.devpass.spaceapp.utils.NetworkResult
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
class LaunchListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var dispatcher = StandardTestDispatcher()

    private val fetchLaunchesRepository = mockk<FetchLaunchesRepository>(relaxed = true)

    private val viewModel by lazy { LaunchListViewModel(fetchLaunchesRepository) }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `GIVEN onSuccess Result with NetworkResult Success WHEN safeLaunchesCall is called THEN set LaunchListUIState Success with Launch to LiveData`() {
        //GIVEN
        val expected = LaunchListUIState.Success(listOf(fakeLaunch))
        coEvery { fetchLaunchesRepository.fetchLaunches()
        } returns NetworkResult.Success(listOf(fakeLaunch))

        //WHEN
        runTest {
            viewModel.getLaunches()
        }

        //THEN
        coVerify { fetchLaunchesRepository.fetchLaunches() }
        assertEquals(expected, viewModel.launches.value)
    }

    @Test
    fun `GIVEN onSuccess Result with NetworkResult Error WHEN safeLaunchesCall is called THEN set LaunchListUIState Error with Exception to LiveData`() {
        //GIVEN
        val fakeException = java.lang.Exception("Error!")

        val expected = LaunchListUIState.Error(fakeException)
        coEvery { fetchLaunchesRepository.fetchLaunches()
        } returns NetworkResult.Error(fakeException)

        //WHEN
        runTest { viewModel.getLaunches() }

        //THEN
        coVerify { fetchLaunchesRepository.fetchLaunches() }
        assertEquals(expected, viewModel.launches.value)
    }

    @Test
    fun `GIVEN onFailure Result WHEN safeLaunchesCall is called THEN set LaunchListUIState Error with Exception to LiveData`() {
        //GIVEN
        val fakeException = java.lang.Exception("Error!")

        val expected = LaunchListUIState.Error(fakeException)
        coEvery { fetchLaunchesRepository.fetchLaunches() } throws fakeException

        //WHEN
        runTest { viewModel.getLaunches() }

        //THEN
        coVerify { fetchLaunchesRepository.fetchLaunches() }
        assertEquals(expected, viewModel.launches.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
