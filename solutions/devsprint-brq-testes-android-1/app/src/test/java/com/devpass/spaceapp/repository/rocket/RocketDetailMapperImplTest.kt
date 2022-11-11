package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert
import org.junit.Test

class RocketDetailMapperImplTest {

    private val mapper: RocketDetailMapperImpl = spyk()

    private val rocketDetailResponse = RocketDetailResponse(
        id = "id",
        name = "name",
        description = "description",
        flickrImages = listOf("image_url")
    )

    private val rocketDetail = RocketDetail(
        id = "id",
        name = "name",
        description = "description",
        image = "image_url"
    )


    @Test
    fun `transform the rocket detailed response model into the rocket model`() {
        //Given
        every { mapper.transformToRocketModel(rocketDetailResponse) } returns rocketDetail

        //When
        val result = mapper.transformToRocketModel(rocketDetailResponse)

        //Then
        Assert.assertSame(rocketDetail, result)

    }
}