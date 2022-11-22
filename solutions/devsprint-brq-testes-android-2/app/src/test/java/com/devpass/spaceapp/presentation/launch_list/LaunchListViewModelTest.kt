package com.devpass.spaceapp.presentation.launch_list

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.api.response.LaunchesResponse
import com.devpass.spaceapp.data.api.response.Links
import com.devpass.spaceapp.data.api.response.Patch
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepositoryImpl
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.LooperMode
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class LaunchListViewModelTest{
    private var instrumentationContext: Context? = null
    private var repository = mockk<FetchLaunchesRepositoryImpl>()
    private  var viewModel = LaunchListViewModel(repository)

    private val uiStateTarget = mockk<Observer<in LaunchListViewModel.LaunchListUIState>>()

    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Test
    fun `test if LoadingState is correctly called`() {

        viewModel.launches.observeForever(uiStateTarget)
        val launch = NetworkResult.Success<List<Launch>>(mockk())
        val loadingLaunch = LaunchListViewModel.LaunchListUIState.Loading

        viewModel.getLaunches()

        verifySequence {
            uiStateTarget.onChanged(loadingLaunch)
        }
    }

    @Test
    fun `test if SuccessState is correctly called`() = runTest {
        val json = instrumentationContext?.resources?.openRawResource(R.raw.launch)

        viewModel.launches.observeForever(uiStateTarget)
        val launch = NetworkResult.Success<List<Launch>>(mockk())
        val successLaunch = LaunchListViewModel.LaunchListUIState.Success(mockk())

        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Success(mockLaunchListModel())


        verifySequence {
            uiStateTarget.onChanged(successLaunch)
        }
    }

    @Test
    fun `when launch state is shown`(){
        viewModel.launches.observeForever(uiStateTarget)
        viewModel.getLaunches()
        assert(viewModel.launches.value is LaunchListViewModel.LaunchListUIState)
    }

    @Test
    fun `test if ErrorState is correctly called`() = runTest {
        viewModel.launches.observeForever(uiStateTarget)
        val launch = NetworkResult.Success<List<Launch>>(mockk())
        val errorLaunch = LaunchListViewModel.LaunchListUIState.Error(Exception())

        viewModel.getLaunches()

        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Error(Exception())


        verifySequence {
            uiStateTarget.onChanged(errorLaunch)
        }
    }


    fun mockLaunchListModel() = listOf(
        Launch(
            name = "string 2",
            number = "1",
            date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
            status = "Success",
            image = "string 1",
            rocketId = "1213fsfa",
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


    private fun mockLinks() = Links(
        Patch(
            "string 1"
        )
    )

}



