package com.devpass.spaceapp.utils

import android.content.Context
import com.google.gson.Gson

fun <T : Any> Context.converterJsonFromRaw(raw: Int, clazz: Class<T>): T {
    val json = this.resources.openRawResource(raw)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(json, clazz)
}