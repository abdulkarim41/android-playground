package com.abdulkarim.androidplayground.di

import com.abdulkarim.data.repoimpl.ProductRepoImpl
import com.abdulkarim.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(productRepoImpl: ProductRepoImpl): ProductRepository

}