package com.devpass.spaceapp.presentation.launch_list

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.devpass.spaceapp.data.api.NetworkService
import com.devpass.spaceapp.robots.Robot
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class LaunchListActivityTest {
    private lateinit var robot: Robot
    private var mockWebServer: MockWebServer? = null
    private lateinit var instrumentationContext: Context

    init {
        mockWebServer = MockWebServer()
        NetworkService.getSpaceXAPI(mockWebServer?.url("/").toString())
    }

    @Before
    fun setup(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        robot = Robot(mockWebServer, instrumentationContext)
    }

    @After
    fun finishActivity(){
        mockWebServer?.shutdown()
    }

    @Test
    fun test(){
        robot.request()
        robot.startActivity()
        robot.checkText()
        robot.checkLoadingIsShowing()
        robot.performClickRecyclerView()
    }
}