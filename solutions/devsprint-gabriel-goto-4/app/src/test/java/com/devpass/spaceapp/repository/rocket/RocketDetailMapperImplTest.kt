package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import org.junit.Assert.*
import org.junit.Test

class RocketDetailMapperImplTest {

    private val transformToRocketModel = RocketDetailMapperImpl()

    @Test
    fun `GIVEN RocketDetailResponse object WHEN transformToRocketModel is called THEN RocketDetail properties stays the same`() {
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

        val result = transformToRocketModel.transformToRocketModel(response)

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

        val result = transformToRocketModel.transformToRocketModel(response)

        assertTrue(result.image.isEmpty())
    }
}