package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class LaunchModelMapperImplTest {
    private lateinit var launchModelMapperImpl : LaunchModelMapperImpl
//    val timestamp = "1666817319"
    val date = Date.from(Instant.now())
    val sdf = SimpleDateFormat("MMMM dd, yyyy")

    @Before
    fun setUp() {
        launchModelMapperImpl = LaunchModelMapperImpl()
    }

    @Test
    fun `test Transforms Launches Response To Launch Success` (){
        val launchModelMock = launchModelMock()
        launchModelMapperImpl.transformToLaunchModel(launchesResponseMockTrue()).let {

            Assert.assertEquals(launchModelMock, it)
        }
    }

    @Test
    fun `test Transforms Launches Response To Launch Fail` (){
        val launchModelMock = launchModelMock()
        launchModelMapperImpl.transformToLaunchModel(launchesResponseMockFalse()).let {

            Assert.assertNotEquals(launchModelMock, it)
        }
    }

    private fun launchesResponseMockTrue() = LaunchesResponse(
        links = Links(Patch("")),
        nameRocket = "",
        date = "",
        status = true,
        flightNumber = 1,
        rocketId = "",
        details = "",
        launchpadId = ""
    )

    private fun launchModelMock() = Launch(
        name= "",
        number = "1",
        date = sdf.format(date),
        status = "Success",
        image = "",
        rocketId = "",
        details = "",
        launchpadId = ""
    )

    private fun launchesResponseMockFalse() = LaunchesResponse(
        links = Links(Patch("")),
        nameRocket = "",
        date = "",
        status = false,
        flightNumber = 0,
        rocketId = "",
        details = "",
        launchpadId = ""
    )


}