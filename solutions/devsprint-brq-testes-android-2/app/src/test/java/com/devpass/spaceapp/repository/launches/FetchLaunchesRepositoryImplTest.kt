package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class FetchLaunchesRepositoryImplTest {
    private val api =  mockk<SpaceXAPIService>()
    private lateinit var mapper: LaunchModelMapper
    private lateinit var fetchLaunchesRepository: FetchLaunchesRepository

    @Before
    fun setup(){
        mapper = LaunchModelMapperImpl()
        fetchLaunchesRepository = FetchLaunchesRepositoryImpl(api, mapper)
    }

    @Test
    fun `test fetchLaunches method is successful`() = runTest {
        val expectedResult = NetworkResult.Success(mockLaunchList())

        coEvery {
            api.fetchNextLaunches(any()).docs
        } returns mockResponse()

        val result = fetchLaunchesRepository.fetchLaunches()

        Assert.assertEquals(expectedResult, result)
    }

    @Test(expected = RuntimeException::class)
    fun `test fetchLaunches method is exception`() = runTest {
        val expectedResult = NetworkResult.Error<Nothing>(mockResponseError())

        coEvery { api.fetchNextLaunches(any()).docs } throws NullPointerException("Error occurred")
        val result = fetchLaunchesRepository.fetchLaunches()

        Assert.assertEquals(expectedResult, result)
    }

    private fun mockResponse(): List<LaunchesResponse> = listOf(
        LaunchesResponse(links=Links(patch=Patch(small="https://images2.imgbox.com/f9/4a/ZboXReNb_o.png")), nameRocket="DemoSat", date="2007-03-21T01:10:00.000Z", status=false, flightNumber=2, rocketId="5e9d0d95eda69955f709d1eb", details="Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage", launchpadId="5e9e4502f5090995de566f86"),
        LaunchesResponse(links=Links(patch=Patch(small="https://images2.imgbox.com/6c/cb/na1tzhHs_o.png")), nameRocket="Trailblazer", date="2008-08-03T03:34:00.000Z", status=false, flightNumber=3, rocketId="5e9d0d95eda69955f709d1eb", details="Residual stage 1 thrust led to collision between stage 1 and stage 2", launchpadId="5e9e4502f5090995de566f86"),
        LaunchesResponse(links=Links(patch=Patch(small="https://images2.imgbox.com/95/39/sRqN7rsv_o.png")), nameRocket="RatSat", date="2008-09-28T23:15:00.000Z", status=true, flightNumber=4, rocketId="5e9d0d95eda69955f709d1eb", details="Ratsat was carried to orbit on the first successful orbital launch of any privately funded and developed, liquid-propelled carrier rocket, the SpaceX Falcon 1", launchpadId="5e9e4502f5090995de566f86"),
        LaunchesResponse(links=Links(patch=Patch(small="https://images2.imgbox.com/ab/5a/Pequxd5d_o.png")), nameRocket="RazakSat", date="2009-07-13T03:35:00.000Z", status=true, flightNumber=5, rocketId="5e9d0d95eda69955f709d1eb", details="Empty", launchpadId="5e9e4502f5090995de566f86"),
        LaunchesResponse(links=Links(patch=Patch(small="https://images2.imgbox.com/73/7f/u7BKqv2C_o.png")), nameRocket="Falcon 9 Test Flight", date="2010-06-04T18:45:00.000Z", status=true, flightNumber=6, rocketId="5e9d0d95eda69973a809d1ec", details="Empty", launchpadId="5e9e4501f509094ba4566f84")
    )

    private fun mockLaunchList() = listOf(
        Launch(
            name = "DemoSat",
            number = "2",
            date = "março 20, 2007",
            status = "Fail",
            image = "https://images2.imgbox.com/f9/4a/ZboXReNb_o.png",
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage",
            launchpadId = "5e9e4502f5090995de566f86"
        ),
        Launch(
            name = "Trailblazer",
            number = "3",
            date = "agosto 03, 2008",
            status = "Fail",
            image = "https://images2.imgbox.com/6c/cb/na1tzhHs_o.png",
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Residual stage 1 thrust led to collision between stage 1 and stage 2",
            launchpadId = "5e9e4502f5090995de566f86"
        ),
        Launch(
            name = "RatSat",
            number = "4",
            date = "setembro 28, 2008",
            status = "Success",
            image = "https://images2.imgbox.com/95/39/sRqN7rsv_o.png",
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Ratsat was carried to orbit on the first successful orbital launch of any privately funded and developed, liquid-propelled carrier rocket, the SpaceX Falcon 1",
            launchpadId = "5e9e4502f5090995de566f86"
        ),
        Launch(
            name = "RazakSat",
            number = "5",
            date = "julho 13, 2009",
            status = "Success",
            image = "https://images2.imgbox.com/ab/5a/Pequxd5d_o.png",
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Empty",
            launchpadId = "5e9e4502f5090995de566f86"
        ),
        Launch(
            name = "Falcon 9 Test Flight",
            number = "6",
            date = "junho 04, 2010",
            status = "Success",
            image = "https://images2.imgbox.com/73/7f/u7BKqv2C_o.png",
            rocketId = "5e9d0d95eda69973a809d1ec",
            details = "Empty",
            launchpadId = "5e9e4501f509094ba4566f84"
        )
    )

    private fun mockResponseError() = IllegalArgumentException("")
}