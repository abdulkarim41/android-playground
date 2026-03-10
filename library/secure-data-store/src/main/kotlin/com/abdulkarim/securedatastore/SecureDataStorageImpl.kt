package com.abdulkarim.securedatastore

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SecureDataStorageImpl @Inject constructor(
    @ApplicationContext context: Context
) : SecureDataStorage {

    companion object {
        private const val PREF_NAME = "secure_storage"
        private const val KEY_ALIAS = "secure_token_key"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val cryptoManager: CryptoManager

    init {
        KeyStoreHelper.generateKeyIfNeeded(KEY_ALIAS)
        cryptoManager = CryptoManager(KEY_ALIAS)
    }

    override fun put(key: String, value: String) {
        val (iv, encrypted) = cryptoManager.encrypt(value)
        prefs.edit {
            putString("${key}_iv", iv)
            putString("${key}_data", encrypted)
            commit()
        }
    }

    override fun get(key: String): String {
        val iv = prefs.getString("${key}_iv", null) ?: return ""
        val encrypted = prefs.getString("${key}_data", null) ?: return ""
        return cryptoManager.decrypt(iv, encrypted)
    }

    override fun remove(key: String) {
        prefs.edit {
            remove("${key}_iv")
            remove("${key}_data")
            commit()
        }
    }

    override fun clear() {
        prefs.edit { clear() }
    }
}