package com.devpass.spaceapp.util

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf.allOf

object UiTests {

    fun isTextDisplayed(id: Int, text: String){
        try {
            onView(allOf(withId(id), withText(text))).check(matches(isDisplayed()))
        }catch (e: java.lang.Exception){
            println(e)
        }
    }

    fun sleep(time: Long){
        Thread.sleep(time)
    }

    fun isShowing(id: Int){
        try {
            onView(withId(id)).check(matches(isDisplayed()))
        }catch (e: Exception){
            println(e)
        }
    }

    fun onPerformRecyclerView(id: Int, position: Int) {
        try {
            onView(withId(id)).perform(
                RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, click()))

        } catch (e: Exception) {
            println(e)
        }
    }
}