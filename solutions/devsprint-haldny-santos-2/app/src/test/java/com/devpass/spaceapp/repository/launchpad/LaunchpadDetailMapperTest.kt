package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LaunchpadDetailMapperTest {

    private lateinit var mapper: LaunchpadDetailMapper

    @Before
    fun setup() {
        mapper = LaunchpadDetailMapperImpl()
    }

    @Test
    fun `GIVEN LaunchpadDetailResponse object WHEN transformToLaunchpadModel is called THEN return same properties`() {
        val response = LaunchpadDetailResponse(
            id = "1",
            name = "Name",
            locality = "New York",
            region = "North America",
            latitude = -12447.8,
            longitude = -2324.7,
            launchAttempts = 3,
            launchSuccesses = 2
        )

        val expected = LaunchpadDetail(
            id = "1",
            name = "Name",
            locality = "New York",
            region = "North America",
            latitude = -12447.8,
            longitude = -2324.7,
            launchAttempts = 3,
            launchSuccesses = 2
        )

        val result = mapper.transformToLaunchpadModel(response)

        assertEquals(expected, result)
    }
}
