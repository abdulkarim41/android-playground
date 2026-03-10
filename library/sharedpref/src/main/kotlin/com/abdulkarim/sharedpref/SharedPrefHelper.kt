package com.abdulkarim.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREF_NAME = "abdulkarim.sharedpref.android.playground"

class SharedPrefHelper(context: Context) {

     val prefs: SharedPreferences by lazy {
        context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun put(key: String, value: Any) {
        prefs.edit {
            when (value) {
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    inline fun <reified T> get(key: String, default: T): T {
        return when (T::class) {
            String::class -> prefs.getString(key, default as String) as T
            Boolean::class -> prefs.getBoolean(key, default as Boolean) as T
            Int::class -> prefs.getInt(key, default as Int) as T
            Long::class -> prefs.getLong(key, default as Long) as T
            Float::class -> prefs.getFloat(key, default as Float) as T
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    fun clear() {
        prefs.edit { clear() }
    }

    fun remove(key: String) {
        prefs.edit { remove(key) }
    }
}