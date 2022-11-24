package com.devpass.spaceapp.domain

import com.devpass.spaceapp.model.RocketDetail
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RocketDetailUseCaseImplTest {

    private val repository = mockk<RocketDetailRepository>(relaxed = true)
    private val mockSubject = mockk<RocketDetailUseCase>()
    private val subject = RocketDetailUseCaseImpl(repository)

    @Test
    fun `should return correctly when id is not empty`() = runTest {
        //Arrange
        val parameter = "id-success"
        val expected = mockk<NetworkResult<RocketDetail>>()

        coEvery {
            repository.fetchRocketDetail(parameter)
        } returns expected

        //Act
        val result = subject.invoke(parameter)

        coVerify {
            repository.fetchRocketDetail(parameter)
        }

        //Assert
        assertEquals(expected, result)
    }

    @Test
    fun `should return an error when id is empty`() = runTest {
        //Arrange
        val parameter = ""
        val expected = NetworkResult.Error<RocketDetail>(IllegalArgumentException("Rocket id should not be empty"))

        coEvery {
            mockSubject.invoke(parameter)
        } returns expected

        //Act
        val result = mockSubject.invoke(parameter)

        //Assert
        assertEquals(expected, result)
    }
}