package com.abdulkarim.securedatastore

interface SecureDataStorage {
    fun put(key: String, value: String)
    fun get(key: String): String
    fun remove(key: String)
    fun clear()
}