package com.example.proiect

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "movie_prefs"
private const val KEY_DARK_THEME = "pref_dark_theme"

// helper pentru SharedPreferences
private fun Context.prefs(): SharedPreferences =
    applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

var Context.isDarkTheme: Boolean
    get() = prefs().getBoolean(KEY_DARK_THEME, false)
    set(value) = prefs().edit().putBoolean(KEY_DARK_THEME, value).apply()
