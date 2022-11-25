package com.devpass.spaceapp.robots

import android.content.Context
import androidx.test.core.app.launchActivity
import com.devpass.spaceapp.R
import com.devpass.spaceapp.presentation.launch_list.LaunchListActivity
import com.devpass.spaceapp.util.UiTests
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class Robot(private val mockWebServer: MockWebServer?, private val context: Context) {
    private val scenario = launchActivity<LaunchListActivity>()

    fun startActivity() {
        scenario.onActivity { it.intent }
    }

    fun checkText(): Robot {
        UiTests.isTextDisplayed(R.id.tv_next_launch, "Next Launches")
        UiTests.sleep(1000)
        return this
    }

    fun checkLoadingIsShowing(): Robot {
        UiTests.isShowing(R.id.lottie_rocket_loading)
        UiTests.sleep(2000)
        return this
    }

    fun performClickRecyclerView(): Robot {
        UiTests.onPerformRecyclerView(R.id.rv_launches, 2)
        UiTests.sleep(3000)
        return this
    }

    fun request(): Robot {
       mockWebServer?.enqueue(MockResponse().setResponseCode(200).setBody(
            context.resources.openRawResource(R.raw.mock_launches_full_response)
                .bufferedReader().use { it.readText() }))
        return this
    }
}