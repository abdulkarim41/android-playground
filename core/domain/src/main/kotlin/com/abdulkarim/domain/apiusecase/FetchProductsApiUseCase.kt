package com.abdulkarim.domain.apiusecase

import android.util.Log
import com.abdulkarim.common.Result
import com.abdulkarim.domain.repository.ProductRepository
import com.abdulkarim.domain.usecase.ApiUseCaseNonParams
import com.abdulkarim.entity.ProductApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProductsApiUseCase @Inject constructor(
    private val productRepository: ProductRepository
): ApiUseCaseNonParams<List<ProductApiEntity>> {

    override suspend fun execute(): Flow<Result<List<ProductApiEntity>>> {
        return productRepository.getProductsApi()
    }

}