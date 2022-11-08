package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

class LaunchModelMapperTest {

    private lateinit var mapper: LaunchModelMapper

    @Before
    fun setup() {
        mapper = LaunchModelMapperImpl()
    }

    @Test
    fun `Verify if the LaunchesResponse is correctly mapped to Launch model`(){

        val launchModel = mapper.transformToLaunchModel(mockLaunchesResponse())
        assertEquals( mockLaunchModel(), launchModel)

    }

    private fun mockLaunchModel() = Launch(
        name = "string 2",
        number = "1",
        date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
        status = "Fail",
        image = "string 1",
        rocketId = "id",
        details = "details",
        launchpadId = "launch id"
    )

    private fun mockLaunchesResponse() = LaunchesResponse(
        links = mockLinks(),
        nameRocket = "string 2",
        date = "2008-08-03T03:34:00.000Z",
        status = false,
        flightNumber = 1,
        rocketId = "id",
        details = "details",
        launchpadId = "launch id"
    )

    private fun mockLinks() = Links(
        Patch(
            "string 1"
        )
    )

}