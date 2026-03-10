package com.abdulkarim.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DATASTORE_NAME = "abdulkarim.datastore.android.playground"

private val Context.dataStore by preferencesDataStore(
    name = DATASTORE_NAME
)

@Singleton
class DatastorePreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DatastorePreferences {

    private val datastore = context.dataStore

    override suspend fun put(key: String, value: Any) {
        datastore.edit { prefs ->
            when (value) {
                is String -> prefs[stringPreferencesKey(key)] = value
                is Int -> prefs[intPreferencesKey(key)] = value
                is Boolean -> prefs[booleanPreferencesKey(key)] = value
                is Long -> prefs[longPreferencesKey(key)] = value
                is Float -> prefs[floatPreferencesKey(key)] = value
                else -> throw IllegalArgumentException("Unsupported type: ${value::class}")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, default: T): Flow<T> {
        val prefKey: Preferences.Key<T> = when (default) {
            is String -> stringPreferencesKey(key) as Preferences.Key<T>
            is Int -> intPreferencesKey(key) as Preferences.Key<T>
            is Boolean -> booleanPreferencesKey(key) as Preferences.Key<T>
            is Long -> longPreferencesKey(key) as Preferences.Key<T>
            is Float -> floatPreferencesKey(key) as Preferences.Key<T>
            else -> throw IllegalArgumentException("Unsupported type: ${default!!::class}")
        }

        return datastore.data.map { prefs -> prefs[prefKey] ?: default }
    }

    override suspend fun remove(key: String) {
        datastore.edit { prefs ->
            prefs.remove(stringPreferencesKey(key))
            prefs.remove(intPreferencesKey(key))
            prefs.remove(booleanPreferencesKey(key))
            prefs.remove(longPreferencesKey(key))
            prefs.remove(floatPreferencesKey(key))
        }
    }

    override suspend fun clear() {
        datastore.edit { it.clear() }
    }

}