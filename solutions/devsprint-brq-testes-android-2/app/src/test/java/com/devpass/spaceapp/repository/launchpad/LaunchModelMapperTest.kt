package com.devpass.spaceapp.repository.launchpad

import com.devpass.spaceapp.repository.launches.LaunchModelMapper
import com.devpass.spaceapp.repository.launches.LaunchModelMapperImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LaunchModelMapperTest : MockLaunchTest() {

    private lateinit var mapper: LaunchModelMapper

    @Before
    fun setup() {
        mapper = LaunchModelMapperImpl()
    }

    @Test
    fun `verify if launchesResponse is correctly mapped to Launch model with Fail status`(){
        val launchModel = mapper.transformToLaunchModel(mockFailLaunchesResponse())
        assertEquals( mockFailLaunchModel(), launchModel)
    }

    @Test
    fun `verify if launchesResponse is correctly mapped to Launch model with Success status`(){
        val launchModel = mapper.transformToLaunchModel(mockSuccessLaunchesResponse())
        assertEquals( mockSuccessLaunchModel(), launchModel)
    }

}