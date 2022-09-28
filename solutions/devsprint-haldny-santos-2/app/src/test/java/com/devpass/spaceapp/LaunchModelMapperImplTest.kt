package com.devpass.spaceapp

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.repository.launches.LaunchModelMapper
import com.devpass.spaceapp.repository.launches.LaunchModelMapperImpl
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException


class LaunchModelMapperImplTest {

    private lateinit var launchModelMapper: LaunchModelMapperImpl

    @Before
    fun init() {
        launchModelMapper = LaunchModelMapperImpl()
    }

    @Test
    fun `should return a Fail status when LaunchResponse status is false`() {
        //ARRANGE
        val defaultLaunches = createLaunchesResponse(status = false)
        val expected = createLaunch(status = "Fail")

        //ACT
        val launch: Launch = launchModelMapper.transformToLaunchModel(defaultLaunches)

        //ASSERT
        assertEquals(expected, launch)
    }

    @Test
    fun `should return a Success status when LaunchRepsonse status is true`() {
        val defaultLaunches = createLaunchesResponse(status = true)
        val expected = createLaunch()

        val launch = launchModelMapper.transformToLaunchModel(defaultLaunches)

        assertEquals(expected, launch)
    }

    @Test
    fun `should return Empty when LaunchResponse detail is null`() {
        val defaultLaunches = createLaunchesResponse(details = null)
        val expected = createLaunch(details = "Empty")

        val launch = launchModelMapper.transformToLaunchModel(defaultLaunches)

        assertEquals(expected, launch)
    }


    @Test
    fun `should return exception when date is invalid`() {
        val defaultLaunches = createLaunchesResponse(date = "0000")
        val expected = createLaunch(date = "Bad formatted date")

        val launch = launchModelMapper.transformToLaunchModel(defaultLaunches)

        assertEquals(expected, launch)
    }

    @Test
    fun `should return complete date when date is a ISO date `() {
        val defaultLaunches = createLaunchesResponse()
        val expected = createLaunch()

        val launch = launchModelMapper.transformToLaunchModel(defaultLaunches)

        assertEquals(expected, launch)
    }

    companion object {
        private val patch = Patch(small = "https://images2.imgbox.com/6c/cb/na1tzhHs_o.png")
        private val pathLinks = Links(patch = patch)
    }

    private fun createLaunchesResponse(
        links: Links = pathLinks,
        nameRocket: String = "FalconSat",
        date: String = "1995-02-25T22:30:00.Z",
        status: Boolean = true,
        flightNumber: Int = 1,
        rocketId: String = "5e9d0d95eda69955f709d1eb",
        details: String? = "Engine failure at 33 seconds and loss of vehicle",
        launchpadId: String = "5e9e4502f5090995de566f86"
    ): LaunchesResponse {
        return LaunchesResponse(
            links = links,
            nameRocket = nameRocket,
            date = date,
            status = status,
            flightNumber = flightNumber,
            rocketId = rocketId,
            details = details,
            launchpadId = launchpadId,
        )
    }

    private fun createLaunch(
        name: String = "FalconSat",
        number: String = "1",
        date: String = "February 25, 1995",
        status: String = "Success",
        image: String = "https://images2.imgbox.com/6c/cb/na1tzhHs_o.png",
        rocketId: String = "5e9d0d95eda69955f709d1eb",
        details: String = "Engine failure at 33 seconds and loss of vehicle",
        launchpadId: String = "5e9e4502f5090995de566f86",
    ): Launch {
        return Launch(name, number, date, status, image, rocketId, details, launchpadId)
    }

}
