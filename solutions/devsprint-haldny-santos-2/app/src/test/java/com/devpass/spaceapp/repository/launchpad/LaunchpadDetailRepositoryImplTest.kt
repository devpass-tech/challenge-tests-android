package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.fakeLaunchpadDetail
import com.devpass.spaceapp.fakeLaunchpadDetailResponse
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
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

    @Test(expected = Exception::class)
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
