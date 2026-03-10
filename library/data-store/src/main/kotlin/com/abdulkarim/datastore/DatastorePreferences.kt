package com.abdulkarim.datastore

import kotlinx.coroutines.flow.Flow

interface DatastorePreferences {
    suspend fun put(key: String, value: Any)
    fun <T> get(key: String, default: T): Flow<T>
    suspend fun remove(key: String)
    suspend fun clear()
}