package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.data.api.response.LaunchpadDetailResponse
import com.devpass.spaceapp.model.LaunchpadDetail
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LaunchpadDetailMapperImplTest {

    private lateinit var response: LaunchpadDetailResponse
    private lateinit var launchpadDetailMapper: LaunchpadDetailMapper
    private lateinit var expected: LaunchpadDetail

    @Before
    fun setup() {
        launchpadDetailMapper = LaunchpadDetailMapperImpl()
        expected = LaunchpadDetail(
            "abc",
            "de",
            "ghi",
            "jkl",
            1.0,
            2.0,
            3,
            2
        )
    }

    @Test
    fun `test transformToLaunchpadModel when response is ok`() {
        response = responseOk()
        val actual = launchpadDetailMapper.transformToLaunchpadModel(response)
        assertEquals(expected, actual)
    }

    fun responseOk(): LaunchpadDetailResponse {
        return LaunchpadDetailResponse(
            "abc",
            "def",
            "ghi",
            "jkl",
            1.0,
            2.0,
            3,
            2
        )
    }
}