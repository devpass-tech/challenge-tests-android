package com.devpass.spaceapp.presentation.launch_list

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.repository.launches.LaunchModelMapper
import com.devpass.spaceapp.repository.launches.LaunchModelMapperImpl
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import com.devpass.spaceapp.R
import io.mockk.verifySequence
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class LaunchListViewModelTest{
    private var instrumentationContext: Context? = null
    //private val api = mockk<SpaceXAPIService>()
    private  var mapper : LaunchModelMapper  = LaunchModelMapperImpl()
//    private  var  repository : FetchLaunchesRepositoryImpl = mockk{
//        coEvery { fetchLaunches() } returns mockk()
//    }
    private var repository = mockk<FetchLaunchesRepositoryImpl>()
    private  var viewModel = LaunchListViewModel(repository)

    private val uiStateTarget = mockk<Observer<in LaunchListViewModel.LaunchListUIState>>()



//    @get:Rule
//    val dispatcherRule = TestDispatcherRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()
//    private val dispatcher = StandardTestDispatcher()


//    @get:Rule
//    val mainCoroutineRule = MainDispatcherRule()

//    @Before
//    fun setup(){
//        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
//    }

    @Test
    fun `loading`(){
        viewModel.launches.observeForever(uiStateTarget)
        val launch = NetworkResult.Success<List<Launch>>(mockk())
        val loadingLaunch = LaunchListViewModel.LaunchListUIState.Loading

        viewModel.getLaunches()

        verifySequence {
            uiStateTarget.onChanged(loadingLaunch)
        }
    }



    @Test
    fun `test if LoadingState is correctly called`() {

        viewModel.launches.observeForever(uiStateTarget)
        val launch = NetworkResult.Success<List<Launch>>(mockk())
        val loadingLaunch = LaunchListViewModel.LaunchListUIState.Loading

        viewModel.getLaunches()

        verifySequence {
            uiStateTarget.onChanged(loadingLaunch)
        }

    //= runTest {
        // given
        //val expectedCall = LaunchListViewModel.LaunchListUIState.Loading

        //when
//        coEvery {
//            viewModel.getLaunches()
//        }
        //advanceUntilIdle()

        //then
        //assertEquals(LaunchListViewModel.LaunchListUIState.Loading,expectedCall)
    }

    @Test
    fun `test if SuccessState is correctly called`() { //= runTest {
        val json = instrumentationContext?.resources?.openRawResource(R.raw.launch)

        viewModel.launches.observeForever(uiStateTarget)
        val launch = NetworkResult.Success<List<Launch>>(mockk())
        val successLaunch = LaunchListViewModel.LaunchListUIState.Success(mockk())

        viewModel.getLaunches()

        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Success(mockLaunchListModel())


        verifySequence {
            uiStateTarget.onChanged(successLaunch)
        }

//        val expectedResult = LaunchListViewModel.LaunchListUIState.Success(mockLaunchListModel())
//
//        coEvery {
//            repository.fetchLaunches()
//        } returns NetworkResult.Success(mockLaunchListModel())
//
//        viewModel.getLaunches()
//
//        assertEquals(viewModel.launches.value, expectedResult)
    }

    @Test
    fun `when launch state is shown`(){
        viewModel.getLaunches()
        assert(viewModel.launches.value is LaunchListViewModel.LaunchListUIState)
    }

    @Test
    fun `test if ErrorState is correctly called`() = runTest {
        val expectedResult =  LaunchListViewModel.LaunchListUIState.Error(Exception())

        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Error(Exception())

        assertEquals(expectedResult,viewModel.launches)
    }

    @Test
    fun `test if returns Success is correctly shown`() = runTest {
        val expectedResult = NetworkResult.Success(mockLaunchListModel())

        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Success(mockLaunchListModel())

        assertEquals(expectedResult,viewModel.launches.value)
    }

    @Test
    fun `test if returns Error is correctly shown`() = runTest {
        val expectedResult = NetworkResult.Error<Nothing>(Exception())

        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Success(mockLaunchListModel())

        assertEquals(expectedResult,viewModel.launches.value)
    }


    //////////////////////////////////////////////////////////















    fun mockLaunchListModel() = listOf(
        Launch(
            name = "string 2",
            number = "1",
            date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
            status = "Success",
            image = "string 1",
            rocketId = "id",
            details = "details",
            launchpadId = "launch id"
        ),
        Launch(
            name = "string 2",
            number = "1",
            date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
            status = "Fail",
            image = "string 1",
            rocketId = "id",
            details = "details",
            launchpadId = "launch id"
        )
    )

    fun mockListLaunchesResponse() = listOf(
        LaunchesResponse(
            links = mockLinks(),
            nameRocket = "string 2",
            date = "2008-08-03T03:34:00.000Z",
            status = true,
            flightNumber = 1,
            rocketId = "id",
            details = "details",
            launchpadId = "launch id"
        )
    )
    private fun mockListLaunchesFailResponse() = listOf(
        LaunchesResponse(
            links = mockLinks(),
            nameRocket = "string 2",
            date = "2008-08-03T03:34:00.000Z",
            status = false,
            flightNumber = 1,
            rocketId = "id",
            details = "details",
            launchpadId = "launch id"
        )
    )

    private fun mockLinks() = Links(
        Patch(
            "string 1"
        )
    )

}



