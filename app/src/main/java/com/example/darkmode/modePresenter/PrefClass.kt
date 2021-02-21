package com.example.darkmode.modePresenter

import android.content.Context
import android.content.SharedPreferences

class PrefClass(context: Context) {
    companion object{
        private const val prefName = "DarkTheme.pref"
    }

    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }
}