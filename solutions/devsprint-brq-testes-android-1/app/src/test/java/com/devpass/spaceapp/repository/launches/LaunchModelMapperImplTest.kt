package com.devpass.spaceapp.repository.launches

import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LaunchModelMapperImplTest {
    private lateinit var launchModelMapperImpl : LaunchModelMapperImpl

    @Before
    fun setUp() {
        launchModelMapperImpl = LaunchModelMapperImpl()
    }

    @Test
    fun `test Transforms Launches Response To Launch Success` (){
        val launchResponse = launchesResponseMockTrue()
        launchModelMapperImpl.transformToLaunchModel(launchResponse).let {

            Assert.assertEquals(launchSuccess(), it)
        }
    }

    @Test
    fun `test Transforms Launches Response To Launch Fail` (){
        val launchesResponse = launchesResponseMockFalse()
        launchModelMapperImpl.transformToLaunchModel(launchesResponse).let {

            Assert.assertEquals(launchFail(), it)
        }
    }

    private fun launchesResponseMockTrue() = LaunchesResponse(
        links = Links(
            patch = Patch(small = "https://images2.imgbox.com/94/f2/NN6Ph45r_o.png")
        ),
        nameRocket = "FalconSat",
        date = "2006-03-24T22:30:00.000Z",
        status = true,
        flightNumber = 1,
        rocketId = "5e9d0d95eda69955f709d1eb",
        details = "Engine failure at 33 seconds and loss of vehicle",
        launchpadId = "5e9e4502f5090995de566f86"
    )

    private fun launchSuccess() = Launch(
            name="FalconSat",
            number= "1",
            date="março 24, 2006",
            status="Success",
            image="https://images2.imgbox.com/94/f2/NN6Ph45r_o.png",
            rocketId="5e9d0d95eda69955f709d1eb",
            details="Engine failure at 33 seconds and loss of vehicle",
            launchpadId="5e9e4502f5090995de566f86"
        )

    private fun launchFail() = Launch(
            name="FalconSat",
            number= "1",
            date="março 24, 2006",
            status="Fail",
            image="https://images2.imgbox.com/94/f2/NN6Ph45r_o.png",
            rocketId="5e9d0d95eda69955f709d1eb",
            details="Engine failure at 33 seconds and loss of vehicle",
            launchpadId="5e9e4502f5090995de566f86"
        )

    private fun launchesResponseMockFalse() = LaunchesResponse(
        links = Links(
            patch = Patch(small = "https://images2.imgbox.com/94/f2/NN6Ph45r_o.png")
        ),
        nameRocket = "FalconSat",
        date = "2006-03-24T22:30:00.000Z",
        status = false,
        flightNumber = 1,
        rocketId = "5e9d0d95eda69955f709d1eb",
        details = "Engine failure at 33 seconds and loss of vehicle",
        launchpadId = "5e9e4502f5090995de566f86"
    )


}