package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.faker.RocketDetailMapperFaker
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

// Pega a classe real -> cria um proxy dela que pode ser manipulado

class RocketDetailRepositoryImplTest {

    // mock - faker

    val api = mockk<SpaceXAPIService>(relaxed = true)
    val mapper = RocketDetailMapperFaker()

    private val subject = RocketDetailRepositoryImpl(
        api = api,
        mapper = mapper,
    )

    @Test(expected = RuntimeException::class)
    fun `Given a Empty id When fetchRocketDetail is Called should throw Exception`() = runTest {
        // Arrange.
        val parameter = ""
        prepareError()

        // Act.
        subject.fetchRocketDetail(parameter)
    }

    fun prepareError() {
        coEvery { api.fetchRocketDetails(any()) } throws IllegalArgumentException()
    }
}
