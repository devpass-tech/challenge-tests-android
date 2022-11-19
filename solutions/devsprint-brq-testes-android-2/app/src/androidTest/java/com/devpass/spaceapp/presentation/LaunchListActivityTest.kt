package com.devpass.spaceapp.presentation

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.R
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launch.LaunchNavigator
import com.devpass.spaceapp.presentation.launch_list.LaunchListActivity
import com.devpass.spaceapp.presentation.launch_list.LaunchListViewModel
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.utils.KoinMockTestRule
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LaunchListActivityTest {

    private val launchNavigator = mockk<LaunchNavigator>(relaxed = true)
    private val repository = mockk<FetchLaunchesRepository>()

    @get:Rule
    val koinTestRule = KoinMockTestRule(
        module {
            viewModel {
                LaunchListViewModel(repository)
            }
            factory { launchNavigator }
        }
    )

    @Test
    fun whenScreenLoaded_andClickOn10thElement_ShouldOpenNextScreen() {
        prepareMocks()
        launchActivity<LaunchListActivity>()

        //onView(withId(R.id.rv_launches)).check(matches(isDisplayed()));

        onView(withId(R.id.rv_launches))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    10,
                    click()
                )
            )

        verify(exactly = 1) {
//            Intent(
//                ApplicationProvider.getApplicationContext(),
//                LaunchListActivity::class.java
//            ).also {
//                it.putExtra("LAUNCH_MODEL",mockGetLaunch())
//            }
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

    private fun mockGetLaunch() =
        Launch(
            name = "FalconAt#10",
            number = "10",
            date = "marrço 24, 2004",
            status = "Success",
            image = "asdada",
            rocketId = "54546464646",
            details = "Engine failure at 33 seconds and less of vehicles",
            launchpadId = "343asdfsdfsf"
        )


    private fun prepareMocks() {
        coEvery {
            repository.fetchLaunches()
        } answers {
            NetworkResult.Success(
                prepareListOfLaunches()
            )
        }
    }

    private fun prepareListOfLaunches() = buildList {
        repeat(30) {
            val items = Launch(
                name = "FalconAt#$it",
                number = it.toString(),
                date = SimpleDateFormat("MMMM dd, yyyy").format(Date.from(Instant.parse("2008-08-03T03:34:00.000Z"))),
                status = if(it % 2 ==0) "Success" else "Fail",
                image = "string $it",
                rocketId = "id",
                details = "details",
                launchpadId = "launch id"
            )
            add(items)
        }
    }

}