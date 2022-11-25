package com.devpass.spaceapp.presentation.launch_list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.KoinMockTestRule
import com.devpass.spaceapp.MainDispatcherRule
import com.devpass.spaceapp.R
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launch.LaunchNavigator
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LaunchListActivityTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    private val launchNavigator = mockk<LaunchNavigator>(relaxed = true)
    private val repository = mockk<FetchLaunchesRepository>()

    @get:Rule
    val koinTestRule = KoinMockTestRule(
        module {
            viewModel {
                LaunchListViewModel(mainDispatcherRule.dispatcher, repository)
            }

            factory { launchNavigator }
        }
    )

    @Test
    fun whenScreenLoaded_andClickOn10thElement_shouldOpenNextScreen() = runTest {

        prepareMocks(
            apiResponse = {
                NetworkResult.Success(prepareListOfLaunches())
            }
        )

        launchActivity<LaunchListActivity>()

        advanceUntilIdle()

        onView(withId(R.id.rv_launches))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(10, click()))

        verify(exactly = 1) {
            launchNavigator.openLaunch(
                any(),
                Launch(
                    name = "FalconSat#10",
                    number = "10",
                    date = "março 24, 2006",
                    status = "Success",
                    image = "https://images2.imgbox.com/94/f2/NN6Ph45r_o.png",
                    rocketId = "5e9d0d95eda69955f709d1eb",
                    details = "Engine failure at 33 seconds and loss of vehicle",
                    launchpadId = "5e9e4502f5090995de566f86"
                )
            )
        }
    }

    @Test
    fun whenScreenLoaded_andClickOn20thElement_ShouldOpenNextScreen() = runTest {

        prepareMocks(
            apiResponse = {
                NetworkResult.Success(prepareListOfLaunches())
            }
        )

        launchActivity<LaunchListActivity>()

        advanceUntilIdle()

        onView(withId(R.id.rv_launches))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(20, click()))

        verify(exactly = 1) {
            launchNavigator.openLaunch(
                any(),
                Launch(
                    name = "FalconSat#20",
                    number = "20",
                    date = "março 24, 2006",
                    status = "Success",
                    image = "https://images2.imgbox.com/94/f2/NN6Ph45r_o.png",
                    rocketId = "5e9d0d95eda69955f709d1eb",
                    details = "Engine failure at 33 seconds and loss of vehicle",
                    launchpadId = "5e9e4502f5090995de566f86"
                )
            )
        }
    }

    @Test
    fun whenScreenInLoading_ShouldDisplayLoadingView() {

        prepareMocks(
            apiResponse = {
                NetworkResult.Success(prepareListOfLaunches())
            }
        )

        launchActivity<LaunchListActivity>()

        onView(withId(R.id.lottie_rocket_loading))
            .check(matches(isDisplayed()))
    }

    private fun prepareMocks(
        apiResponse: () -> NetworkResult<List<Launch>>,
    ) {
        coEvery { repository.fetchLaunches() } returns apiResponse()
    }

    private fun prepareListOfLaunches() = buildList {

        repeat(30) {
            val item = Launch(
                name = "FalconSat#$it",
                number = it.toString(),
                date = "março 24, 2006",
                status = if (it % 2 == 0) "Success" else "Fail",
                image = "https://images2.imgbox.com/94/f2/NN6Ph45r_o.png",
                rocketId = "5e9d0d95eda69955f709d1eb",
                details = "Engine failure at 33 seconds and loss of vehicle",
                launchpadId = "5e9e4502f5090995de566f86"
            )

            add(item)
        }
    }
}