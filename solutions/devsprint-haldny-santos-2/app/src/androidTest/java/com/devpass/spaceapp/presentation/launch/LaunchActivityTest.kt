package com.devpass.spaceapp.presentation.launch

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.adevinta.android.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward
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
    fun givenLaunchActivityWhenClickOnViewMoreThenGoToLaunchpadDetailsActivityWithRocketDataFilled() {
        goToLastItem()

        swipeViewPagerForward()
        swipeViewPagerForward()
        clickOn("VIEW MORE")

        assertDisplayed(R.id.map)
    }

    @Test
    fun givenLaunchActivityWhenClickOnRocketCardThenGoToRocketDetailsActivityWithRocketDataFilled() {
        goToLastItem()

        swipeViewPagerForward()
        clickOn(R.id.rocket_card_view)

        sleep(LOAD_DETAILS_DELAY)

        assertDisplayed(R.id.textViewNameRocketDetails)
        assertDisplayed(R.id.imageViewRocketDetails)
    }

    private fun goToLastItem() {
        sleep(INITIAL_LOADING_DELAY)
        clickListItem(R.id.rv_launches, 19)
        assertDisplayed(R.id.tvTitle, "DSCOVR")
    }

    private companion object {
        const val INITIAL_LOADING_DELAY = 5000L
        const val LOAD_DETAILS_DELAY = 2000L
    }
}
