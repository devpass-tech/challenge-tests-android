package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.details
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.launch
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.launchDetails
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.launchStatusFail
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.launchStatusSuccess
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.response
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.statusFail
import com.devpass.spaceapp.repository.launches.LaunchModelMapperHelperTest.statusSuccess
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class LaunchModelMapperImplTest {

    var transformToLaunchModel = LaunchModelMapperImpl()

    @Before
    fun setup() {
        Locale.setDefault(Locale.US)
    }

    @Test
    fun `GIVEN LaunchesResponse object WHEN transformToLaunchModel is called THEN Launch properties stays the same`() {
        //Given
        val expected = launch
        //When
        val result = transformToLaunchModel.transformToLaunchModel(response)
        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN empty image WHEN transformToRocketModel called THEN details is empty`() {
        //Given
        val expected = launch.image.isEmpty()
        //When
        val result = transformToLaunchModel.transformToLaunchModel(response).image.isEmpty()
        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN success status WHEN transformToLaunchModel is called THEN status is success`() {
        //Given
        val expected = launchStatusSuccess.status
        //When
        val result = transformToLaunchModel.transformToLaunchModel(statusSuccess).status
        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN failed status WHEN transformToLaunchModel is called THEN status is failed`() {
        //Given
        val expected = launchStatusFail.status
        //When
        val result = transformToLaunchModel.transformToLaunchModel(statusFail).status
        //Then
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN empty details WHEN transformToLaunchModel called THEN details is empty`() {
        //Given
        val expected = launchDetails.details.isEmpty()
        //When
        val result = transformToLaunchModel.transformToLaunchModel(details).details.isEmpty()
        //Then
        assertEquals(expected, result)
    }
}