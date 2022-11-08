package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
class RocketDetailRepositoryImplTest {

    private val serviceMock: SpaceXAPIService = mockk()
    private val mapperMock: RocketDetailMapper = mockk()
    private lateinit var repository: RocketDetailRepository

    @Before
    fun setUp() {
        repository = RocketDetailRepositoryImpl(serviceMock, mapperMock)
    }

    @Test
    fun `should be return NetworkResult success when response ok`() = runTest {
        val mockResponseOk = mockResponseOk()
        val mockDomainFromResponseOk = mockDomainFromResponseOk()
        val expectedResult = NetworkResult.Success(mockDomainFromResponseOk)

        coEvery {
            serviceMock.fetchRocketDetails(anyString())
        } returns mockResponseOk

        every {
            mapperMock.transformToRocketModel(mockResponseOk)
        } returns mockDomainFromResponseOk

        val result = repository.fetchRocketDetail(anyString())

        result `should be equal to` expectedResult
    }

    @Test(expected = RuntimeException::class)
    fun `should be return NetworkResult error when response error`() = runTest {
        val mockResponseError = mockResponseError()
        val expectedResult = NetworkResult.Error<Nothing>(mockResponseError)

        coEvery {
            serviceMock.fetchRocketDetails(anyString())
        } throws mockResponseError

        val result = repository.fetchRocketDetail(anyString())

        result `should be equal to` expectedResult
    }

    @Test(expected = RuntimeException::class)
    fun `should be throws RuntimeException when response error`() = runTest {
        val mockResponseError = mockResponseError()

        coEvery {
            serviceMock.fetchRocketDetails(anyString())
        } throws mockResponseError

        repository.fetchRocketDetail(anyString())
    }

    private fun mockResponseOk() = RocketDetailResponse(
        "",
        "",
        "",
        emptyList()
    )

    private fun mockResponseError() = IllegalArgumentException(anyString())

    private fun mockDomainFromResponseOk() = RocketDetail(
        "",
        "",
        "",
        ""
    )
}