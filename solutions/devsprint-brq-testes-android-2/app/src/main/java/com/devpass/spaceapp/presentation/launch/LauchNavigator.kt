package com.devpass.spaceapp.presentation.launch

import android.content.Context
import android.content.Intent
import com.devpass.spaceapp.model.Launch

interface LaunchNavigator {

    fun openLaunch(context: Context, launchModel: Launch)
}

internal class LaunchNavigatorImpl : LaunchNavigator {

    private companion object {
        private const val LAUNCH_MODEL = "LAUNCH_MODEL"
    }

    override fun openLaunch(context: Context, launchModel: Launch) {
        val intent = Intent(context,LaunchActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(LAUNCH_MODEL, launchModel)
        context.startActivity(intent)
    }

}