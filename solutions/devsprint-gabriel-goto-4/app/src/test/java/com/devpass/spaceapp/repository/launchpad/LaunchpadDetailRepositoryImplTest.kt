package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.FakeLaunchpadDetail.fakeLaunchpadDetail
import com.devpass.spaceapp.FakeLaunchpadDetail.fakeLaunchpadDetailResponse
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LaunchpadDetailRepositoryImplTest {

    private val api = mockk<SpaceXAPIService>(relaxed = true)
    private val mapper = mockk<LaunchpadDetailMapper>(relaxed = true)
    private val repository = LaunchpadDetailRepositoryImpl(api, mapper)

    @Test
    fun `GIVEN LaunchpadDetailResponse WHEN fetchLaunchpad is called THEN return a NetworkResult success`() {
        runTest {

            //GIVEN
            val launchpadDetailResponse = fakeLaunchpadDetailResponse
            val launchpadDetail = fakeLaunchpadDetail

            every {
                mapper.transformToLaunchpadModel(launchpadDetailResponse)
            } returns launchpadDetail

            val expected = NetworkResult.Success(launchpadDetail)

            coEvery {
                api.fetchLaunchpadDetails(ID)
            } returns launchpadDetailResponse

            //WHEN
            val result = repository.fetchLaunchpad(ID)

            //THEN
            coVerify { api.fetchLaunchpadDetails(ID) }
            assertEquals(expected, result)

        }

    }

    @Test(expected = RuntimeException::class)
    fun `GIVEN LaunchpadDetailResponse WHEN fetchLaunchpad is called THEN return a NetworkResult error`() {
        runTest {
            //GIVEN
            val expected = NetworkResult.Error<Any>(RuntimeException())

            coEvery {
                api.fetchLaunchpadDetails(ID)
            } throws RuntimeException()

            //WHEN
            val result = repository.fetchLaunchpad(ID)

            //THEN
            coVerify { api.fetchLaunchpadDetails(ID) }
            assertEquals(expected, result)
        }
    }

    companion object {
        const val ID = "ANY_ID"
    }
}