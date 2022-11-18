package com.devpass.spaceapp.presentation.rocket_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devpass.spaceapp.MainDispatcherRule
import com.devpass.spaceapp.domain.RocketDetailUseCase
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class RocketDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val rocketDetailUseCase = mockk<RocketDetailUseCase>()

    private val uiStateTarget = mockk<Observer<in RocketDetailsUiState>>(relaxed = true)

    private val subject = RocketDetailsViewModel(
        rocketDetailUseCase,
    )

    @Test
    fun loadRocketDetails_withValidRocketId_shouldExecuteSuccessFlow() = runTest {
        // Arrange
        val parameter = "success-id"

        subject.uiState.observeForever(uiStateTarget)
        val rocketDetail = NetworkResult.Success<RocketDetail>(mockk())

        prepare(
            rocketDetail = rocketDetail
        )

        // Act
        subject.loadRocketDetails(parameter)

        // Assert
        verifySequence {
            uiStateTarget.onChanged(RocketDetailsUiState.Loading)
            uiStateTarget.onChanged(RocketDetailsUiState.Success(rocketDetail.data))
        }
    }

    @Test
    fun loadRocketDetails_withValidRocketIdCalledTwice_shouldExecuteSuccessFlow() = runTest {
        // Arrange
        val parameter = "success-id"

        subject.uiState.observeForever(uiStateTarget)
        val rocketDetail = NetworkResult.Success<RocketDetail>(mockk())

        prepare(
            rocketDetail = rocketDetail
        )

        // Act
        subject.loadRocketDetails(parameter)
        subject.loadRocketDetails(parameter)

        // Assert
        verifySequence {
            uiStateTarget.onChanged(RocketDetailsUiState.Loading)
            uiStateTarget.onChanged(RocketDetailsUiState.Success(rocketDetail.data))
            uiStateTarget.onChanged(RocketDetailsUiState.Loading)
            uiStateTarget.onChanged(RocketDetailsUiState.Success(rocketDetail.data))
        }
    }

    @Test
    fun loadRocketDetails_withValidRocketIdAndError_shouldExecuteErrorFlow() = runTest {
        // Arrange
        val parameter = "error-id"

        subject.uiState.observeForever(uiStateTarget)
        val rocketDetail = NetworkResult.Error<RocketDetail>(Exception())

        prepare(
            rocketDetail = rocketDetail
        )

        // Act
        subject.loadRocketDetails(parameter)

        // Assert
        verifySequence {
            uiStateTarget.onChanged(RocketDetailsUiState.Loading)
            uiStateTarget.onChanged(RocketDetailsUiState.Error(rocketDetail.exception))
        }
    }

    @Test
    fun loadRocketDetails_withIOError_shouldExecuteErrorFlow() = runTest {
        // Arrange
        val parameter = "error-id"

        subject.uiState.observeForever(uiStateTarget)
        val rocketDetail = NetworkResult.Error<RocketDetail>(RocketDetailException(parameter))

        prepare(
            ioError = true, rocketDetail = rocketDetail
        )

        // Act
        subject.loadRocketDetails(parameter)

        // Assert
        verifySequence {
            uiStateTarget.onChanged(RocketDetailsUiState.Loading)
            uiStateTarget.onChanged(RocketDetailsUiState.Error(RocketDetailException(parameter)))
        }
    }

    @Test
    fun loadRocketDetails_withOtherState_shouldDo() = runTest {
        // Arrange
        val parameter = "success-id"

        subject.uiState.observeForever(uiStateTarget)
        val rocketDetail = NetworkResult.Success<RocketDetail>(mockk())

        prepare(
            rocketDetail = rocketDetail
        )

        // Act
        subject.loadRocketDetails(parameter)

        // Assert
        verifySequence {
            uiStateTarget.onChanged(RocketDetailsUiState.Loading)
            uiStateTarget.onChanged(RocketDetailsUiState.Success(rocketDetail.data))
        }
    }

    fun prepare(
        ioError: Boolean = false,
        rocketDetail: NetworkResult<RocketDetail>,
    ) {
        if (ioError) {
            coEvery { rocketDetailUseCase(any()) } answers {

                throw RocketDetailException(firstArg() as String)
            }
        } else {
            coEvery { rocketDetailUseCase(any()) } returns rocketDetail
        }
    }

    data class RocketDetailException(
        val id: String,
    ) : Exception()
}