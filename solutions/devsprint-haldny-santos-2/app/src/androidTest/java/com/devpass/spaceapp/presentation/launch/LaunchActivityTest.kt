package com.devpass.spaceapp.presentation.launch

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.devpass.spaceapp.R
import com.devpass.spaceapp.presentation.launch_list.LaunchListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LaunchListActivity::class.java)

    @Test
    fun barista_givenLaunchActivityWhenClickOnViewMoreThenGoToLaunchpadDetailsActivityWithRocketDataFilled() {
        sleep(5000L)
        clickListItem(R.id.rv_launches, 19)
        assertDisplayed(R.id.tvTitle, "DSCOVR")
        clickOn("LAUNCHPAD")
        clickOn("VIEW MORE")
        assertDisplayed(R.id.map)
    }
}
