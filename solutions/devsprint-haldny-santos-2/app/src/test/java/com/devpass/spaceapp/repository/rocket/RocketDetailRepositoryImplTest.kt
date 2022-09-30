package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.fakeRocketDetail
import com.devpass.spaceapp.fakeRocketDetailResponse
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RocketDetailRepositoryImplTest {

    private val api = mockk<SpaceXAPIService>()
    private val mapper = mockk<RocketDetailMapper>()
    private val repository by lazy { RocketDetailRepositoryImpl(api, mapper) }

    @Test
    fun `GIVEN RocketDetailRepositoryImpl WHEN send an id valid THEN return a rocket detail`() {
        runTest {
            // GIVEN
            coEvery { api.fetchRocketDetails(rocketId) } returns fakeRocketDetailResponse
            every { mapper.transformToRocketModel(any()) } returns fakeRocketDetail
            val expectedResponse = NetworkResult.Success(fakeRocketDetail)

            // WHEN
            val result = repository.fetchRocketDetail(rocketId)

            // THEN
            coVerify { api.fetchRocketDetails(rocketId) }
            assertEquals(expectedResponse, result)
        }
    }

    @Test(expected = Exception::class)
    fun `GIVEN RocketDetailRepositoryImpl WHEN fetchRocketDetail is called and an error happens THEN return a error`() {
        runTest {
            // GIVEN
            coEvery { api.fetchRocketDetails(rocketId) } throws Exception(EXCEPTION_MSG)
            val expectedResponse = NetworkResult.Error<Any>(Exception(EXCEPTION_MSG))

            // WHEN
            val result = repository.fetchRocketDetail(rocketId)

            // THEN
            coVerify { api.fetchRocketDetails(rocketId) }
            assertEquals(expectedResponse, result)
        }
    }

    private companion object {
        const val EXCEPTION_MSG = "errorMsg"
        const val rocketId = "10"
    }
}
