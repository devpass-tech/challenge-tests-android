package com.devpass.spaceapp.presentation.launch

import android.app.Activity
import android.content.Intent
import com.devpass.spaceapp.model.Launch

interface LaunchNavigator {

    fun openLaunch(context: Activity, data: Launch)
}

private const val LAUNCH_MODEL = "LAUNCH_MODEL"

internal class LaunchNavigatorImpl : LaunchNavigator {

    override fun openLaunch(context: Activity, data: Launch) {
        val intent = Intent(context, LaunchActivity::class.java)
        intent.putExtra(LAUNCH_MODEL, data)

        context.startActivity(intent)
    }
}
