package com.abdulkarim.domain.repository

import com.abdulkarim.entity.ProductApiEntity
import com.abdulkarim.common.Result
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductsApi() : Flow<Result<List<ProductApiEntity>>>

}