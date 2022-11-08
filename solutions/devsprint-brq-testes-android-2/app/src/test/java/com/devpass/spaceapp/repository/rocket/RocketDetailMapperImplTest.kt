package com.devpass.spaceapp.repository.rocket

import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.model.RocketDetail
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RocketDetailMapperImplTest {

    private lateinit var rocketDetailMapperImpl: RocketDetailMapperImpl

    @Before
    fun setup() {
        rocketDetailMapperImpl = RocketDetailMapperImpl()
    }

    @Test
    fun `test transformToRocketModel success when flickrImages is not empty`() {
        val expected = rocketDetailMock("test")
        rocketDetailMapperImpl.transformToRocketModel(rocketDetailResponseMock(listMock())).let {
            Assert.assertEquals(expected, it)
        }
    }

    private fun rocketDetailMock(string: String) = RocketDetail(
        id = "id",
        name = "name",
        description = "description",
        image = string
    )

    private fun rocketDetailResponseMock(list: List<String>) = RocketDetailResponse(
        id = "id",
        name = "name",
        description = "description",
        flickrImages = list
    )
    private fun listMock() = listOf("test","test", "test")

}