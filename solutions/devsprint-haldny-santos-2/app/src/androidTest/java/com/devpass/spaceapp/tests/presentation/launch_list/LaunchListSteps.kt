package com.devpass.spaceapp.tests.presentation.launch_list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.rule.BaristaRule
import com.devpass.spaceapp.R
import com.devpass.spaceapp.tests.utils.waitUntilGoneAction
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchListSteps {

    @get:Rule
    val baristaRule = BaristaRule.create(LaunchListActivity::class.java)

    @Given("The app started")
    fun startApp() {
        baristaRule.launchActivity()
    }

    @And("Wait for list is visible")
    fun waitForListIsVisible() {
        onView(ViewMatchers.withId(R.id.lottie_rocket_loading))
            .perform(waitUntilGoneAction(5000L))
    }

    @Then("I see launch list screen")
    fun iSeeLaunchListScreen() {
        assertDisplayed(R.id.rv_launches)
    }

    @When("I click in {int}")
    fun iClickInPosition(position: Int) {
        clickListItem(R.id.rv_launches, position)
    }

    @Then("I see LaunchActivity with {string}")
    fun iSeeLaunchActivityWithTitle(title: String) {
        assertDisplayed(R.id.tvTitle, title)
    }

}
