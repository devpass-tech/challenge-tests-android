package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LaunchpadDetailRepositoryImplTest {

    private var api = mockk<SpaceXAPIService>()
    private var mapper = mockk<LaunchpadDetailMapper>()
    private lateinit var repository: LaunchpadDetailRepositoryImpl

    @Before
    fun setUp() {
        repository = LaunchpadDetailRepositoryImpl(api, mapper)
    }

    @Test
    fun `should return networkSuccess when response is valid`() = runTest {
        val launchpadDetailResponse = getLaunchpadResponseMock()
        val launchpadDetail = getLaunchpadDetailMock()
        val networkSuccessExpected = NetworkResult.Success(launchpadDetail)

        coEvery {
            repository.fetchLaunchpad(String())
        } returns NetworkResult.Success(launchpadDetail)

        every {
            mapper.transformToLaunchpadModel(launchpadDetailResponse)
        } returns launchpadDetail

        coEvery {
            api.fetchLaunchpadDetails(String())
        } returns launchpadDetailResponse

        val networkResult = repository.fetchLaunchpad(String())

        Assert.assertEquals(networkSuccessExpected, networkResult)
    }

    @Test(expected = Exception::class)
    fun `should return error when get an exception`() = runTest {
        val expectedError = NetworkResult.Error<Nothing>(Exception())

        coEvery {
            api.fetchLaunchpadDetails(String())
        } throws Exception()

        val result = repository.fetchLaunchpad(String())

        Assert.assertEquals(expectedError, result)
    }

    private fun getLaunchpadResponseMock() = LaunchpadDetailResponse(
        id = "",
        name = "",
        locality = "",
        region = "",
        latitude = 0.0,
        longitude = 0.0,
        launchAttempts = 0,
        launchSuccesses = 0
    )

    private fun getLaunchpadDetailMock() = LaunchpadDetail(
        id = "",
        name = "",
        locality = "",
        region = "",
        latitude = 0.0,
        longitude = 0.0,
        launchAttempts = 0,
        launchSuccesses = 0
    )
}