package com.devpass.spaceapp.repository.launches

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchLaunchesRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var api: SpaceXAPIService
    private lateinit var mapper: LaunchModelMapper
    private lateinit var repository: FetchLaunchesRepository

    @Before
    fun setup() {
        api = mockk(relaxed = true)
        mapper = LaunchModelMapperImpl()
        repository = FetchLaunchesRepositoryImpl(api, mapper)
    }

    @Test
    fun `when fetch launches result sucess`() = runTest {
        val expected = networkResultSucess()

        coEvery { api.fetchNextLaunches(any()).docs } returns mockResponse()

        val result = fetchLaunches()

        assertEquals(result, expected)
    }

    @Test(expected = Throwable::class)
    fun `when fetch launches result has error`() = runTest {
        val expected = networkResultError()

        coEvery { repository.fetchLaunches() } throws Exception()

        val result = fetchLaunches()

        assertEquals(result, expected)
    }

    private suspend fun fetchLaunches() =
        repository.fetchLaunches()

    private fun networkResultSucess(): NetworkResult.Success<List<Launch>> =
        NetworkResult.Success(mockListLaunch())

    private fun networkResultError(): NetworkResult.Error<Nothing> =
        NetworkResult.Error(Exception())

    private fun mockListLaunch() = listOf(
        Launch(
            name = "DemoSat",
            number = "2",
            date = "março 20, 2007",
            status = "Fail",
            image = "https://images2.imgbox.com/f9/4a/ZboXReNb_o.png",
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage",
            launchpadId = "5e9e4502f5090995de566f86"
        )
    )

    private fun mockResponse() = listOf(
            LaunchesResponse(
                links=Links(
                    patch=Patch(
                        small="https://images2.imgbox.com/f9/4a/ZboXReNb_o.png"
                    )
                ),
                nameRocket="DemoSat",
                date="2007-03-21T01:10:00.000Z",
                status=false,
                flightNumber=2,
                rocketId="5e9d0d95eda69955f709d1eb",
                details="Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage",
                launchpadId="5e9e4502f5090995de566f86"
            ),
        )
}