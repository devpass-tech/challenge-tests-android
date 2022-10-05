package com.devpass.spaceapp.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.devpass.spaceapp.R
import com.devpass.spaceapp.presentation.launch_list.LaunchListActivity
import com.devpass.spaceapp.presentation.launch_list.LaunchViewHolder
import com.devpass.spaceapp.utils.waitUntilGoneAction
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LaunchListActivity::class.java)

    @Test
    fun givenLaunchListActivityWhenClickOnListItem10ThenGoToLaunchActivityWithItem10DataFilled() {
        onView(withId(R.id.lottie_rocket_loading))
            .perform(waitUntilGoneAction(5000L))

        onView(
            allOf(
                withId(R.id.rv_launches),
                withContentDescription(R.string.launch_list_content_description),
                isDisplayed()
            )
        ).perform(actionOnItemAtPosition<LaunchViewHolder>(9, click()))

        onView(
            allOf(
                withId(R.id.tvTitle),
                withText("CRS-2")
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun barista_givenLaunchListActivityWhenClickOnListItem10ThenGoToLaunchActivityWithItem10DataFilled() {
        sleep(5000L)
        clickListItem(R.id.rv_launches, 9)
        assertDisplayed(R.id.tvTitle, "CRS-2")
    }
}
