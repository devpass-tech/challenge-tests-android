package com.devpass.spaceapp.presentation.launch_list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchListActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LaunchListActivity::class.java)

    @Test
    fun runTest() {
        onView(withText(""))
    }
}