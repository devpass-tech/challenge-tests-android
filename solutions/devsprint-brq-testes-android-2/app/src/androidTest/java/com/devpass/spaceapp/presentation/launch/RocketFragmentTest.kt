package com.devpass.spaceapp.presentation.launch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devpass.spaceapp.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RocketFragmentTest {

    @get:Rule
    val mainDispatcherRule  = MainDispatcherRule(StandardTestDispatcher())


}