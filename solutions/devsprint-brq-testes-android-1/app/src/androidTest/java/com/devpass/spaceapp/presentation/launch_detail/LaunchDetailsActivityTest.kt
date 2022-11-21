package com.devpass.spaceapp.presentation.launch_detail

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.KoinMockTestRule
import com.devpass.spaceapp.domain.RocketDetailUseCase
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launch.LaunchActivity
import com.devpass.spaceapp.presentation.launchpad_detail.LaunchpadDetailViewModel
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsViewModel
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepository
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import com.devpass.spaceapp.R
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class LaunchDetailsActivityTest {

    private val useCase = mockk<RocketDetailUseCase>()
    private val repository = mockk<LaunchpadDetailRepository>()

    @get:Rule
    val koinTestRule = KoinMockTestRule(
        module {
            viewModel {
                RocketDetailsViewModel(useCase)
            }
            viewModel {
                LaunchpadDetailViewModel(repository)
            }
        }
    )

    @Test
    fun whenAtLaunchActivity_AndClickOnViewMore_ShouldOpenLaunchDetailsActivity() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), LaunchActivity::class.java)
            .also { it.putExtra(LAUNCH_MODEL, getLaunchMock()) }

        val scenario = launchActivity<LaunchActivity>(intent = intent)

        onView(withText("DETAILS")).perform(click())
        onView(withText("VIEW MORE")).perform(click())

        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))

        scenario.close()
    }

    private fun getLaunchMock() = Launch(
        name="FalconSat",
        number="1",
        date="March 24, 2006",
        status="Fail",
        image="https://images2.imgbox.com/94/f2/NN6Ph45r_o.png",
        rocketId="5e9d0d95eda69955f709d1eb",
        details="Engine failure at 33 seconds and loss of vehicle",
        launchpadId="5e9e4502f5090995de566f86"
    )

    companion object {
        private const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }
}