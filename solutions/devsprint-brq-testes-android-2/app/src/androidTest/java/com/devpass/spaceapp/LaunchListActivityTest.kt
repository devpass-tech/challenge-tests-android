package com.devpass.spaceapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launch_list.LaunchListActivity
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@RunWith(AndroidJUnit4::class)
class LaunchListActivityTest {

    private val launchNavigator = mockk<LaunchNavigator>(true)
    private val repository = mockk<FetchLaunchesRepository>()
//
//    @get:Rule
//    val koinTestRule = KoinMockTestRule(
//        module {
//            viewModel {
//                LaunchListViewModel(repository)
//            }
//            factory { launchNavigator }
//        }
//    )

    @Test
    fun whenScreenLoaded_andClickOn10thElement_ShouldOpenNextScreen(){
        prepareMocks()
        launchActivity<LaunchListActivity>()

        onView(withId(R.id.rv_launches))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10,click()))

        verify(exactly = 1) {
            launchNavigator.openLaunch(
                any(),
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
            )
        }
    }

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