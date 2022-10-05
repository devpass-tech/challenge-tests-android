package com.devpass.spaceapp.utils

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers.any
import java.util.concurrent.TimeoutException

class WaitUntilGoneAction(
    private val timeout: Long
) : ViewAction {

    override fun getConstraints(): Matcher<View> = any(View::class.java)

    override fun getDescription() = "wait up to $timeout milliseconds for the view to be gone"

    override fun perform(uiController: UiController, view: View) {
        val endTime = System.currentTimeMillis() + timeout

        do {
            if (view.visibility == View.GONE) return
            uiController.loopMainThreadForAtLeast(50)
        } while (System.currentTimeMillis() < endTime)

        throw PerformException.Builder()
            .withActionDescription(description)
            .withCause(TimeoutException("Waited $timeout milliseconds"))
            .withViewDescription(HumanReadables.describe(view))
            .build()
    }
}

fun waitUntilGoneAction(timeout: Long) = WaitUntilGoneAction(timeout)
