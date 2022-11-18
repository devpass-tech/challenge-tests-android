package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import org.junit.Assert
import org.junit.Test

class LaunchpadDetailMapperImplTest {

    private val launchpadDetailMapperImpl = LaunchpadDetailMapperImpl()

    @Test
    fun `test transform Response to Domain success` (){
        val expected = launchpadDetailMock()

        val result = launchpadDetailMapperImpl.transformToLaunchpadModel(launchpadDetailResponseMock())

        Assert.assertEquals(expected, result)

    }

    private fun launchpadDetailMock() = LaunchpadDetail(
        id = "",
        name = "",
        locality = "",
        region = "",
        latitude = 0.0,
        longitude = 0.0,
        launchAttempts = 0,
        launchSuccesses = 0
    )

    private fun launchpadDetailResponseMock() = LaunchpadDetailResponse(
        id = "",
        name = "",
        locality = "",
        region = "",
        latitude = 0.0,
        longitude = 0.0,
        launchAttempts = 0,
        launchSuccesses = 0
    )
}

