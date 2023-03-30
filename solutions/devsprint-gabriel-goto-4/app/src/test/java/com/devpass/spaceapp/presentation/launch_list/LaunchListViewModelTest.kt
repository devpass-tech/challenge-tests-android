package com.devpass.spaceapp.presentation.launch_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.rules.MainDispatcherRule
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class LaunchListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val repository = mockk<FetchLaunchesRepository>()

    private lateinit var subject: LaunchListViewModel
    val observer = mockk<Observer<in LaunchListViewModel.LaunchListUIState>>()

    val error = Exception()
    val networkSuccess = mockk<List<Launch>>(relaxed = true)
    val networkError = IOException()

    @Before
    fun setup() {
        every { observer.onChanged(any()) } just runs
        coEvery { repository.fetchLaunches() } returns NetworkResult.Error(error)

        subject = LaunchListViewModel(repository, mainDispatcherRule.dispatcher)

        subject.launches.observeForever(observer)
    }

    @Test
    fun `GIVEN launchList WHEN network error THEN return ui error state`() {
        // Arrange.
        val expected = LaunchListViewModel.LaunchListUIState.Error(error)

        // Act.
        subject.getLaunches()
        // Advanced coroutines here until finish
        mainDispatcherRule.dispatcher.scheduler.advanceUntilIdle()

        // Assert
        verifySequence {
            observer.onChanged(LaunchListViewModel.LaunchListUIState.Loading)
            observer.onChanged(expected)
            observer.onChanged(LaunchListViewModel.LaunchListUIState.Loading)
            observer.onChanged(expected)
        }

    }

    @Test
    fun `GIVEN launchList WHEN network success THEN return ui success state`() {
        // Arrange.
        coEvery { repository.fetchLaunches() } returns NetworkResult.Success(networkSuccess)
        val expected = LaunchListViewModel.LaunchListUIState.Success(networkSuccess)

        // Act.
        subject.getLaunches()
        // Advanced coroutines here until finish
        mainDispatcherRule.dispatcher.scheduler.advanceUntilIdle()

        // Assert
        verifySequence {
            observer.onChanged(LaunchListViewModel.LaunchListUIState.Loading)
            observer.onChanged(expected)
            observer.onChanged(LaunchListViewModel.LaunchListUIState.Loading)
            observer.onChanged(expected)
        }
    }

    @Test
    fun `GIVEN launchList WHEN network failure THEN return ui error state`() {
        // Arrange.
        coEvery{repository.fetchLaunches()} throws networkError
        val expected = LaunchListViewModel.LaunchListUIState.Error(networkError)

        // Act.
        subject.getLaunches()
        // Advanced coroutines here until finish
        mainDispatcherRule.dispatcher.scheduler.advanceUntilIdle()

        // Assert
        verifySequence {
            observer.onChanged(LaunchListViewModel.LaunchListUIState.Loading)
            observer.onChanged(expected)
            observer.onChanged(LaunchListViewModel.LaunchListUIState.Loading)
            observer.onChanged(expected)
        }
    }
}
