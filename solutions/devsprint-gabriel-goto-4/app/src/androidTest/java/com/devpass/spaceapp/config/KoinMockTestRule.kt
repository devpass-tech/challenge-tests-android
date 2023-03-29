package com.devpass.spaceapp.config

import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class KoinMockTestRule(
     private val modules: List<Module>
) : TestWatcher() {

    constructor(module: Module): this(listOf(module))

    override fun starting(description: Description) {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(modules)
        }
    }

    override fun finished(description: Description) {
        stopKoin()
    }
}
