package com.devpass.spaceapp.presentation.rocket_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RocketDetailsViewModelTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: RocketDetailsViewModel
    private val repository = mockk<RocketDetailRepository>()

    @Before
    fun setup() {
        viewModel = RocketDetailsViewModel(repository)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `test loadRocketDetails when is successful`() {
        val rocketDetail = mockk<RocketDetail>()
        val expected = RocketDetailsUiState.Success(rocketDetail)

        coEvery {
            repository.fetchRocketDetail("")
        } returns NetworkResult.Success(rocketDetail)

        runTest { viewModel.loadRocketDetails("") }

        val value = viewModel.uiState.value
        assertEquals(expected, value)
    }

    @Test
    fun `test loadRocketDetails when is not successful`() {
        val e = Exception()
        val expected = RocketDetailsUiState.Error(e)

        coEvery {
            repository.fetchRocketDetail("")
        } returns NetworkResult.Error(e)

        runTest { viewModel.loadRocketDetails("") }

        val value = viewModel.uiState.value
        assertEquals(expected, value)
    }

    @Test
    fun `test loadRocketDetails on failure`() {
        val throwable = Throwable()
        val expected = RocketDetailsUiState.Error(throwable)

        coEvery {
            repository.fetchRocketDetail("")
        } throws throwable

        runTest { viewModel.loadRocketDetails("") }

        val value = viewModel.uiState.value
        assertEquals(expected, value)
    }


}