package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.fakeLaunch
import com.devpass.spaceapp.fakeLaunchesPageResponse
import com.devpass.spaceapp.fakeQueryParams
import com.devpass.spaceapp.tests.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchLaunchesRepositoryImplTest {

    private val api = mockk<SpaceXAPIService>(relaxed = true)

    private val mapper = mockk<LaunchModelMapper>(relaxed = true)

    private val repository by lazy {
        FetchLaunchesRepositoryImpl(api, mapper)
    }

    @Test
    fun `GIVEN FetchLaunchesRepositoryImpl WHEN fetchLaunches is called THEN get a Launch list as result`() {
        runTest {
            // Mock fake objects
            val body = fakeQueryParams
            val response = fakeLaunchesPageResponse
            val launch = fakeLaunch

            // GIVEN
            every { mapper.transformToLaunchModel(any()) } returns launch
            val expectedResponse = NetworkResult.Success(listOf(launch))
            coEvery { api.fetchNextLaunches(body) } returns response

            // WHEN
            val result = repository.fetchLaunches()

            // THEN (Assertion)
            coVerify { api.fetchNextLaunches(body) }
            assertEquals(expectedResponse, result)
        }
    }

    @Test(expected = Exception::class)
    fun `GIVEN FetchLaunchesRepositoryImpl WHEN fetchLaunches is called THEN throws a response body error`() {
        runTest {
            val body = fakeQueryParams

            // GIVEN
            val expectedResponse = NetworkResult.Error<Any>(Exception(EXCEPTION_MSG))
            coEvery { api.fetchNextLaunches(body) } throws Exception(EXCEPTION_MSG)

            // WHEN
            val result = repository.fetchLaunches()

            // THEN (Assertion)
            coVerify { api.fetchNextLaunches(body) }
            assertEquals(expectedResponse, result)
        }
    }

    private companion object {
        const val EXCEPTION_MSG = "errorMsg"
    }
}
