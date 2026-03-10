package com.abdulkarim.sharedpref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREF_NAME = "abdulkarim.sharedpref.android.playground"

class SharedPrefHelper(context: Context) {

     private val prefs: SharedPreferences by lazy {
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
                else -> throw IllegalArgumentException("Unsupported type: ${value::class}")
            }
        }
    }

    /**
     * Retrieve a value of type String, Boolean, Int, Long, Float
     */
    fun <T> get(key: String, default: T): T {
        @Suppress("UNCHECKED_CAST")
        return when (default) {
            is String -> prefs.getString(key, default) as T
            is Boolean -> prefs.getBoolean(key, default) as T
            is Int -> prefs.getInt(key, default) as T
            is Long -> prefs.getLong(key, default) as T
            is Float -> prefs.getFloat(key, default) as T
            else -> throw IllegalArgumentException("Unsupported type: ${default?.javaClass?.kotlin}")
        }
    }


    fun clear() {
        prefs.edit { clear() }
    }

    fun remove(key: String) {
        prefs.edit { remove(key) }
    }
}