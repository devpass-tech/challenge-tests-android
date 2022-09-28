package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class RocketDetailMapperTest {

    private lateinit var rocketDetailMapper: RocketDetailMapper

    @Before
    fun setup() {
        rocketDetailMapper = RocketDetailMapperImpl()
    }

    @Test
    fun `GIVEN RocketDetailResponse object WHEN transformToRocketModel called THEN RocketDetail properties are the same`() {
        val response = RocketDetailResponse(
            id = "1",
            name = "Rocket",
            description = "Its a rocket",
            flickrImages = listOf("link 1", "link 2")
        )

        val expected = RocketDetail(
            id = "1",
            name = "Rocket",
            description = "Its a rocket",
            image = "link 1"
        )

        val result = rocketDetailMapper.transformToRocketModel(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN empty flickerImages list WHEN transformToRocketModel called THEN RocketDetail image is empty`() {
        val response = RocketDetailResponse(
            id = "1",
            name = "Rocket",
            description = "Its a rocket",
            flickrImages = listOf()
        )

        val expected = RocketDetail(
            id = "1",
            name = "Rocket",
            description = "Its a rocket",
            image = ""
        )

        val result = rocketDetailMapper.transformToRocketModel(response)

        assertEquals(expected, result)
        assertTrue(result.image.isEmpty())
    }
}
