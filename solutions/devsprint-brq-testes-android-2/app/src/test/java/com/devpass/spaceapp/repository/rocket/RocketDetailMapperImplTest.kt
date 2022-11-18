package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class RocketDetailMapperImplTest {
    private lateinit var mapper: RocketDetailMapper

    @Before
    fun setup(){
        mapper = RocketDetailMapperImpl()
    }

    @Test
    fun `test to verify that the transformToRocketModel method is successful`(){
        assertEquals(mockDetail(), mapper.transformToRocketModel(mockResponse()))
    }

    @Test
    fun `test to verify that the transformToRocketModel method with empty list`(){
        assertEquals(mockDetailEmptyList(), mapper.transformToRocketModel(mockResponseEmptyList()))
    }

    private fun mockResponse() = RocketDetailResponse(
        id="5e9d0d95eda69955f709d1eb",
        name="Falcon 1",
        description="The Falcon 1 was an expendable launch system privately developed and " +
                "manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became " +
                "the first privately-developed liquid-fuel launch vehicle to go into " +
                "orbit around the Earth.",
        flickrImages= listOf("https://imgur.com/DaCfMsj.jpg", "https://imgur.com/azYafd8.jpg"))

    private fun mockResponseEmptyList() = RocketDetailResponse(
        id="5e9d0d95eda69955f709d1eb",
        name="Falcon 1",
        description="The Falcon 1 was an expendable launch system privately developed and " +
                "manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became " +
                "the first privately-developed liquid-fuel launch vehicle to go into " +
                "orbit around the Earth.",
        flickrImages= listOf())

    private fun mockDetail() = RocketDetail(
        id = "5e9d0d95eda69955f709d1eb",
        name = "Falcon 1",
        description = "The Falcon 1 was an expendable launch system privately developed and " +
                "manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became " +
                "the first privately-developed liquid-fuel launch vehicle to go into " +
                "orbit around the Earth.",
        image = "https://imgur.com/DaCfMsj.jpg")

    private fun mockDetailEmptyList() = RocketDetail(
        id = "5e9d0d95eda69955f709d1eb",
        name = "Falcon 1",
        description = "The Falcon 1 was an expendable launch system privately developed and " +
                "manufactured by SpaceX during 2006-2009. On 28 September 2008, Falcon 1 became " +
                "the first privately-developed liquid-fuel launch vehicle to go into " +
                "orbit around the Earth.",
        image = "")
}