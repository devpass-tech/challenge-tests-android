package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LaunchpadDetailRepositoryImplTest {

    private val api = mockk<SpaceXAPIService>()
    private lateinit var mapper: LaunchpadDetailMapper
    private lateinit var launchpadDetailRepository: LaunchpadDetailRepository

    @Before
    fun setup() {
        mapper = LaunchpadDetailMapperImpl()
        launchpadDetailRepository = LaunchpadDetailRepositoryImpl(api, mapper)
    }

    @Test
    fun ` test fetchLaunchpad when response is ok`() = runTest {

        //given
        val expectedResult = NetworkResult.Success(mockLaunchpadDetail())

        //when
        coEvery {
            api.fetchLaunchpadDetails("abc")
        } returns mockLaunchpadDetailResponse()

        val result = launchpadDetailRepository.fetchLaunchpad("abc")

        //then
        assertEquals(expectedResult, result)
    }

    @Test(expected = RuntimeException::class)
    fun ` test fetchLaunchpad when response is not ok`() = runTest {

        //given
        val expectedResult = NetworkResult.Error<Nothing>(mockResponseError())

        //when
        coEvery {
            mapper.transformToLaunchpadModel(api.fetchLaunchpadDetails(any()))
        } throws mockResponseError()

        val result = launchpadDetailRepository.fetchLaunchpad("abc")

        //then
        assertEquals(expectedResult, result)
    }

    private fun mockLaunchpadDetail() = LaunchpadDetail(
        "abc",
        "def",
        "ghi",
        "jkl",
        1.0,
        2.0,
        3,
        2
    )

    private fun mockResponseError() = RuntimeException()

    private fun mockLaunchpadDetailResponse() = LaunchpadDetailResponse(
        id = "abc",
        name = "def",
        locality = "ghi",
        region = "jkl",
        latitude = 1.0,
        longitude = 2.0,
        launchAttempts = 3,
        launchSuccesses = 2
    )
}




