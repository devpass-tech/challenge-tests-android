package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LaunchpadDetailMapperImplTest {

    private lateinit var launchpadDetailMapperImpl : LaunchpadDetailMapperImpl

    @Before
    fun setUp() {
        launchpadDetailMapperImpl = LaunchpadDetailMapperImpl()
    }

    @Test
    fun `test transform Response to Domain success` (){
        val expected = LaunchpadDetailMock()
        launchpadDetailMapperImpl.transformToLaunchpadModel(LaunchpadDetailResponseMock()).let {
            Assert.assertEquals(expected, it)
        }
    }

    private fun LaunchpadDetailMock() = LaunchpadDetail(
        id = "",
        name = "",
        locality = "",
        region = "",
        latitude = 0.0,
        longitude = 0.0,
        launchAttempts = 0,
        launchSuccesses = 0
    )

    private fun LaunchpadDetailResponseMock() = LaunchpadDetailResponse(
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