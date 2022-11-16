package com.devpass.spaceapp.presentation.launchpad_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devpass.spaceapp.MainDispatcherRule
import com.devpass.spaceapp.domain.LaunchpadDetailUseCase
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.presentation.TestConstants.PARAMETER_ERROR_ID
import com.devpass.spaceapp.presentation.TestConstants.PARAMETER_SUCCESS_ID
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsViewModelTest
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class LaunchpadDetailViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<LaunchpadDetailUseCase>()

    private val uiStateTarget = mockk<Observer<in LaunchpadDetailUIState>>(relaxed = true)
    private val subject = LaunchpadDetailViewModel(useCase)

    @Test
    fun loadLaunchpadDetails_withValidLaunchpadId_shouldExecuteSuccessFlow() = runTest {
        subject.uiState.observeForever(uiStateTarget)
        val launchpadDetail = NetworkResult.Success<LaunchpadDetail>(mockk())
        prepare(
            launchpadDetail = launchpadDetail
        )

        subject.safeLaunchpadDetailCall(PARAMETER_SUCCESS_ID)

        verifySequence {
            uiStateTarget.onChanged(LaunchpadDetailUIState.Loading)
            uiStateTarget.onChanged(LaunchpadDetailUIState.Success(launchpadDetail.data))
        }
    }

    @Test
    fun loadLaunchpadDetails_withValidLaunchpadIdCalledTwice_shouldExecuteSuccessFlow() = runTest {
        subject.uiState.observeForever(uiStateTarget)
        val launchpadDetail = NetworkResult.Success<LaunchpadDetail>(mockk())
        prepare(
            launchpadDetail = launchpadDetail
        )

        subject.safeLaunchpadDetailCall(PARAMETER_SUCCESS_ID)
        subject.safeLaunchpadDetailCall(PARAMETER_SUCCESS_ID)

        verifySequence {
            uiStateTarget.onChanged(LaunchpadDetailUIState.Loading)
            uiStateTarget.onChanged(LaunchpadDetailUIState.Success(launchpadDetail.data))
            uiStateTarget.onChanged(LaunchpadDetailUIState.Loading)
            uiStateTarget.onChanged(LaunchpadDetailUIState.Success(launchpadDetail.data))
        }
    }

    @Test
    fun loadLaunchpadDetails_withValidLaunchpadIdAndError_shouldExecuteErrorFlow() = runTest {
        subject.uiState.observeForever(uiStateTarget)
        val launchpadDetail = NetworkResult.Error<LaunchpadDetail>(Exception())
        prepare(
            launchpadDetail = launchpadDetail
        )

        subject.safeLaunchpadDetailCall(PARAMETER_ERROR_ID)

        verifySequence {
            uiStateTarget.onChanged(LaunchpadDetailUIState.Loading)
            uiStateTarget.onChanged(LaunchpadDetailUIState.Error(launchpadDetail.exception))
        }
    }

    @Test
    fun loadLaunchpadDetails_withIOError_shouldExecuteErrorFlow() = runTest {
        subject.uiState.observeForever(uiStateTarget)
        val launchpadDetail = NetworkResult
            .Error<LaunchpadDetail>(LaunchpadDetailException(PARAMETER_ERROR_ID))
        prepare(
            launchpadDetail = launchpadDetail
        )

        subject.safeLaunchpadDetailCall(PARAMETER_ERROR_ID)

        verifySequence {
            uiStateTarget.onChanged(LaunchpadDetailUIState.Loading)
            uiStateTarget.onChanged(LaunchpadDetailUIState
                .Error(LaunchpadDetailException(PARAMETER_ERROR_ID)))
        }
    }

    @Test
    fun loadLaunchpadDetails_withOtherState_shouldDo() = runTest {
        subject.uiState.observeForever(uiStateTarget)
        val launchpadDetail = NetworkResult.Success<LaunchpadDetail>(mockk())
        prepare(
            launchpadDetail = launchpadDetail
        )

        subject.safeLaunchpadDetailCall(PARAMETER_SUCCESS_ID)

        verifySequence {
            uiStateTarget.onChanged(LaunchpadDetailUIState.Loading)
            uiStateTarget.onChanged(LaunchpadDetailUIState
                .Success(launchpadDetail.data))
        }
    }

    private fun prepare(
        ioError: Boolean = false,
        launchpadDetail: NetworkResult<LaunchpadDetail>,
    ) {
        if (ioError) {
            coEvery { useCase(any()) } answers {
                throw LaunchpadDetailException(firstArg() as String)
            }
        } else {
            coEvery { useCase(any()) } returns launchpadDetail
        }
    }

    data class LaunchpadDetailException(
        val id: String,
    ) : Exception()
}