package com.devpass.spaceapp.presentation.launch_list

import android.content.Intent
import android.net.Network
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.App
import com.devpass.spaceapp.KoinMockTestRule
import com.devpass.spaceapp.MainDispatcherRule
import com.devpass.spaceapp.R
import com.devpass.spaceapp.domain.RocketDetailUseCase
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launch.LaunchActivity
import com.devpass.spaceapp.presentation.launch.LaunchNavigator
import com.devpass.spaceapp.presentation.launchpad_detail.LaunchpadDetailViewModel
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsViewModel
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class LaunchListActivityTestNavigation {

    private val rocketDetailUseCase = mockk<RocketDetailUseCase>()
    private val launchpadDetailRepository = mockk<LaunchpadDetailRepository>()


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

    private val launchNavigator = mockk<LaunchNavigator>(relaxed = true)
    private val repository = mockk<FetchLaunchesRepository>()

    @get:Rule
    val kointestRule = KoinMockTestRule(
        module {
            viewModel {
                LaunchListViewModel(mainDispatcherRule.dispatcher, repository)
            }
            viewModel {
                RocketDetailsViewModel(rocketDetailUseCase)
            }

            viewModel {
                LaunchpadDetailViewModel(launchpadDetailRepository)
            }

            factory { launchNavigator }
        }
    )

    @Test
    fun whenClickAnyElement_ShouldOpenLaunchActivity() = runTest {
        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Success(prepareListOfLaunches())

        launchActivity<LaunchListActivity>()

        advanceUntilIdle()

        onView(ViewMatchers.withId(R.id.rv_launches))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    20,
                    click()
                )
            )


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
    fun whenClickAnyElement_ShouldLaunchActivityOpen() = runTest {
        coEvery {
            repository.fetchLaunches()
        } returns NetworkResult.Success(prepareListOfLaunches())

        val scenario = launchActivity<LaunchListActivity>()

        advanceUntilIdle()

        onView(ViewMatchers.withId(R.id.rv_launches))
            .perform(click())

        onView(ViewMatchers.withId(R.id.toolbar))
            .check(matches(isDisplayed()))

        scenario.close()
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




