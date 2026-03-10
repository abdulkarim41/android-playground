package com.abdulkarim.securedatastore

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SecureDataStorageModule {

    @Binds
    @Singleton
    abstract fun bindSecureStorage(
        secureDataStorageImpl: SecureDataStorageImpl
    ): SecureDataStorage
}