package com.devpass.spaceapp.repository.launches

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchLaunchesRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val api: SpaceXAPIService = mockk(relaxed = true)
    private val mapper = mockk<LaunchModelMapper>()
    private val repository = FetchLaunchesRepositoryImpl(api, mapper)
    private val mockResponse = listOf(mockk<LaunchesResponse>(relaxed = true))
    private val mockLaunch = mockk<Launch>()
    private val mockListLaunch = listOf(mockLaunch)

    @Test
    fun `when fetch launches result sucess`() = runTest {
        val expected = networkResultSucess()

        coEvery { api.fetchNextLaunches(any()).docs } returns mockResponse

        every { mapper.transformToLaunchModel(any()) } returns mockLaunch

        val result = fetchLaunches()

        assertEquals(result, expected)
    }

    @Test(expected = Throwable::class)
    fun `when fetch launches result has error`() = runTest {
        val expected = networkResultError()

        coEvery { fetchLaunches() } throws Exception()

        val result = fetchLaunches()

        assertEquals(result, expected)
    }

    private suspend fun fetchLaunches() =
        repository.fetchLaunches()

    private fun networkResultSucess(): NetworkResult.Success<List<Launch>> =
        NetworkResult.Success(mockListLaunch)

    private fun networkResultError(): NetworkResult.Error<Nothing> =
        NetworkResult.Error(Exception())
}