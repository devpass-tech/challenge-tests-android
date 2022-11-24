package com.devpass.spaceapp.repository.rocket

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.api.response.RocketDetailResponse
import com.devpass.spaceapp.utils.NetworkResult
import com.devpass.spaceapp.utils.getJsonResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class RocketDetailRepositoryTest {

    private val rocketDetailRepository  = mockk<RocketDetailRepositoryImpl>()
    private val api = mockk<SpaceXAPIService>()
    private lateinit var mapper: RocketDetailMapper
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        mapper = RocketDetailMapperImpl()
    }

    @Test
    fun `Verify if the repository returns Network Success with data`(){
        runTest {
            val response = context.getJsonResponse(R.raw.rocket_v4_response, RocketDetailResponse::class.java)
            val rocketDetail = mapper.transformToRocketModel(response)

            coEvery {
                api.fetchRocketDetails(any())
            } returns response

            coEvery {
                rocketDetailRepository.fetchRocketDetail(any())
            } returns NetworkResult.Success(rocketDetail)

            rocketDetailRepository.fetchRocketDetail(String()).apply {
                Assert.assertEquals(NetworkResult.Success(rocketDetail), this)
            }

        }
    }

    @Test(expected = Throwable::class)
    fun `Verify if the repository returns Network Error with exception`(){
        runTest {
            coEvery {
                rocketDetailRepository.fetchRocketDetail(any())
            } throws Throwable()

            rocketDetailRepository.fetchRocketDetail(String()).apply {
                Assert.assertEquals(NetworkResult.Error<Nothing>(Throwable()), this)
            }

            coVerify(exactly = 1) {
                rocketDetailRepository.fetchRocketDetail(any())
            }
        }
    }

}