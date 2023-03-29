package com.devpass.spaceapp.presentation.launch_list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.devpass.spaceapp.R
import com.devpass.spaceapp.config.KoinMockTestRule
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launch.LaunchNavigator
import com.devpass.spaceapp.repository.launches.FetchLaunchesRepository
import com.devpass.spaceapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class LaunchListActivityTest {

    val repository = mockk<FetchLaunchesRepository>()
    // IntentTestRule

    val launchNavigator = mockk<LaunchNavigator>(relaxed = true)

    @get:Rule
    val kointestRule = KoinMockTestRule(
        module {
            viewModel {
                LaunchListViewModel(
                    repository,
                    UnconfinedTestDispatcher(),
                )
            }

            factory {
                launchNavigator
            }
        }
    )

    @Test
    fun given_LaunchListActivity_WHEN_Loaded_with_success_THEN_display_recycler_view() {
        prepare()

        // Verificar se o RecyclerView está visível
        onView(withId(R.id.rv_launches)).check(matches(isDisplayed()))
    }

    @Test
    fun given_LaunchListActivity_WHEN_Loaded_with_success_THEN_toolbar_should_display_correctly() {
        prepare()

        onView(withId(R.id.tv_toolbar_title)).check(matches(withText(R.string.title_toolbar_launch_list_activity)))
    }

    @Test
    fun given_LaunchListActivity_WHEN_Loaded_with_success_and_item_clicked_THEN_should_navigate_to_next_screen() {
        prepare()

        // Fazer scroll e clicar no primeiro item do RecyclerView
        onView(withId(R.id.rv_launches))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))

        // Verificar se a nova Activity ou Fragment foi aberto após o clique, por exemplo, verificando se algum elemento específico está visível
        verify(exactly = 1) {
            launchNavigator.openLaunch(any(), fakeLaunch)
        }
    }

    fun prepare() {
        coEvery { repository.fetchLaunches() } returns NetworkResult.Success(
            listOf(
                fakeLaunch
            )
        )

        ActivityScenario.launch(LaunchListActivity::class.java)
    }

    val fakeLaunch = Launch(
        date = "May 30, 2020",
        name = "Rocket",
        number = "1",
        status = "Success",
        rocketId = "RocketId",
        details = "Details",
        launchpadId = "LaunchpadId",
        image = "small"
    )
}
