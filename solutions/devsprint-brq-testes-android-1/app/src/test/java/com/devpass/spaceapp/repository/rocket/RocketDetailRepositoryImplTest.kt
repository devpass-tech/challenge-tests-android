package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RocketDetailRepositoryImplTest {

    private val spaceXAPIService = mockk<SpaceXAPIService>(relaxed = true)
    private val rocketDetailMapper = mockk<RocketDetailMapper>(relaxed = true)
    private val subject = RocketDetailRepositoryImpl(spaceXAPIService, rocketDetailMapper)

    @Test
    fun `should be return NetworkResult success when response ok`() = runTest {
        val parameter = "parameter-id"
        val rocketDetailResponse = mockk<RocketDetailResponse>()
        val rocketDetail = mockk<RocketDetail>()
        val expectedResultRocketDetail = NetworkResult.Success(rocketDetail)

        coEvery {
            spaceXAPIService.fetchRocketDetails(id = parameter)
        } returns rocketDetailResponse

        every {
            rocketDetailMapper.transformToRocketModel(rocketDetailResponse)
        } returns rocketDetail

        val resultRocketDetail = subject.fetchRocketDetail(id = parameter)

        coVerify(exactly = 1) {
            spaceXAPIService.fetchRocketDetails(id = parameter)
        }

        assertEquals(resultRocketDetail, expectedResultRocketDetail)
    }

    @Test(expected = RuntimeException::class)
    fun `should be return NetworkResult error when response error`() = runTest {
        val parameter = "parameter-id"
        val errorResponse = mockk<ErrorResponseRocketDetail>()
        val expectedResultRocketDetail = NetworkResult.Error<RocketDetail>(errorResponse)

        coEvery {
            spaceXAPIService.fetchRocketDetails(id = parameter)
        } throws errorResponse

        val resultRocketDetail = subject.fetchRocketDetail(id = parameter)

        coVerify(exactly = 1) {
            spaceXAPIService.fetchRocketDetails(id = parameter)
        }

        assertEquals(resultRocketDetail, expectedResultRocketDetail)
    }

    @Test(expected = RuntimeException::class)
    fun `should be throws RuntimeException when response error`() = runTest {
        val parameter = "parameter-id"
        val errorResponse = mockk<ErrorResponseRocketDetail>()

        coEvery {
            spaceXAPIService.fetchRocketDetails(id = parameter)
        } throws errorResponse

        subject.fetchRocketDetail(id = parameter)

        coVerify(exactly = 1) {
            spaceXAPIService.fetchRocketDetails(id = parameter)
        }
    }

    class ErrorResponseRocketDetail : Exception()
}