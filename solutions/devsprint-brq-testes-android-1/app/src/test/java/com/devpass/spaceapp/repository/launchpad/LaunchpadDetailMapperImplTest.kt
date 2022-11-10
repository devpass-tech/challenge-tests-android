package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class LaunchpadDetailMapperImplTest {

    private lateinit var launchpadDetailMapperImpl : LaunchpadDetailMapperImpl

    @Test
    fun `test transform Response to Domain success` (){
        launchpadDetailMapperImpl = LaunchpadDetailMapperImpl()
        val expected = LaunchpadDetailMock()
        launchpadDetailMapperImpl.transformToLaunchpadModel(LaunchpadDetailResponseMock()).let {
            Assert.assertEquals(expected, it)
        }
    }

    private fun LaunchpadDetailMock(): LaunchpadDetail {
        val launchpadDetailData = mockk<LaunchpadDetail>()
        return launchpadDetailData
    }

    private fun LaunchpadDetailResponseMock(): LaunchpadDetailResponse {
        val launchpadDetailResponseData = mockk<LaunchpadDetailResponse>()
        return launchpadDetailResponseData
    }
}
