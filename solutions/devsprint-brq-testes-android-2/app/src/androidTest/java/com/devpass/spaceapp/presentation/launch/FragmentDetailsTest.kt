package com.devpass.spaceapp.presentation.launch

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.R
import com.devpass.spaceapp.model.Launch
import com.devpass.spaceapp.presentation.launchpad_detail.LaunchpadDetailViewModel
import com.devpass.spaceapp.presentation.rocket_detail.RocketDetailsViewModel
import com.devpass.spaceapp.repository.launchpad.LaunchpadDetailRepository
import com.devpass.spaceapp.repository.rocket.RocketDetailRepository
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class FragmentDetailsTest {

    private val rocketDetail: RocketDetailRepository = mockk()
    private val launchpadDetail: LaunchpadDetailRepository = mockk()

    @get:Rule
    val koinTestRule = startKoin {
        module {
            viewModel {
                RocketDetailsViewModel(get())
            }
            viewModel {
                LaunchpadDetailViewModel(get())
            }
            single { mockk<RocketDetailRepository>() }
            single { mockk<LaunchpadDetailRepository>() }
        }
    }

    @Test
    fun testViewMore(){
        val intent = Intent(ApplicationProvider.getApplicationContext(), LaunchActivity::class.java).also {
            it.putExtra("LAUNCH_MODEL", mockLaunchModel())
        }
        val scenario  = launchActivity<LaunchActivity>(intent)

        onView(withText("LAUNCHPAD")).perform(click())
        onView(withText("VIEW MORE")).perform(click())

        onView(withId(R.id.map)).check(ViewAssertions.matches(isDisplayed()))
        scenario.close()
    }

    private fun mockLaunchModel() = Launch(
        "Falcon 2",
        "123456",
        "Abril, 12 de 2022",
        "Success",
        "img1",
        "id",
        "Empty",
        "outro id"
    )
}