package com.devpass.spaceapp

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.repository.launches.LaunchModelMapper
import com.devpass.spaceapp.repository.launches.LaunchModelMapperImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException


class LaunchModelMapperImplTest {

    private lateinit var launchModelMapper: LaunchModelMapperImpl
    private val patch = Patch(small = "https://images2.imgbox.com/6c/cb/na1tzhHs_o.png")
    private val links = Links(patch = patch)

    @Before
    fun init(){
        launchModelMapper = LaunchModelMapperImpl()
    }

    @Test
    fun `should return a Fail status when LaunchResponse status is false`(){
        //ARRANGE
        val launchResponse = LaunchesResponse(
            links = links,
            nameRocket = "FalconSat",
            date = "2006-03-24T22:30:00.000Z",
            status = false,
            flightNumber = 1,
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Engine failure at 33 seconds and loss of vehicle",
            launchpadId = "5e9e4502f5090995de566f86",
        )

        //ACT
        val launch: Launch = launchModelMapper.transformToLaunchModel(launchResponse)

        //ASSERT
        Assert.assertSame("Fail", launch.status)
    }

    @Test
    fun `should return a Success status when LaunchRepsonse status is true`(){
        val launchResponse = LaunchesResponse(
            links = links,
            nameRocket = "FalconSat",
            date = "2006-03-24T22:30:00.000Z",
            status = true,
            flightNumber = 1,
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = "Engine failure at 33 seconds and loss of vehicle",
            launchpadId = "5e9e4502f5090995de566f86",
        )

        val launch = launchModelMapper.transformToLaunchModel(launchResponse)

        Assert.assertSame("Success", launch.status)
    }

    @Test
    fun `should return Empty when LaunchResponse detail is null`(){
        val launchResponse = LaunchesResponse(
            links = links,
            nameRocket = "FalconSat",
            date = "2006-03-24T22:30:00.000Z",
            status = false,
            flightNumber = 1,
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = null,
            launchpadId = "5e9e4502f5090995de566f86",
        )

        val launch = launchModelMapper.transformToLaunchModel(launchResponse)

        Assert.assertEquals("Empty", launch.details)
    }


    @Test(expected = Exception::class)
    fun `should return exception when date is invalid`(){
        val launchResponse = LaunchesResponse(
            links = links,
            nameRocket = "FalconSat",
            date = "0000",
            status = false,
            flightNumber = 1,
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = null,
            launchpadId = "5e9e4502f5090995de566f86",
        )

        val launch = launchModelMapper.transformToLaunchModel(launchResponse)
    }

    @Test
    fun `should return complete date when date is a ISO date `(){
        val launchResponse = LaunchesResponse(
            links = links,
            nameRocket = "FalconSat",
            date = "1995-02-25T22:30:00.Z",
            status = false,
            flightNumber = 1,
            rocketId = "5e9d0d95eda69955f709d1eb",
            details = null,
            launchpadId = "5e9e4502f5090995de566f86",
        )

        val launch = launchModelMapper.transformToLaunchModel(launchResponse)

        Assert.assertEquals("February 25, 1995", launch.date)
    }

}