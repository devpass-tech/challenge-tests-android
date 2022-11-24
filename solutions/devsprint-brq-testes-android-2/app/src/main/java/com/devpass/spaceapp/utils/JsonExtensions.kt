package com.devpass.spaceapp.utils

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson

fun <T: Any> Context.getJsonResponse(@RawRes id: Int, clazz: Class<T>): T{
    val json = this.resources.openRawResource(id)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(json, clazz)
}