package com.abdulkarim.data.repoimpl

import com.abdulkarim.common.Result
import com.abdulkarim.data.apiservice.ProductApiService
import com.abdulkarim.data.mapper.ProductApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.repository.ProductRepository
import com.abdulkarim.entity.ProductApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val productApiService: ProductApiService,
    private val productApiMapper: ProductApiMapper,

    ) : ProductRepository {

    override suspend fun getProductsApi(): Flow<Result<List<ProductApiEntity>>> {
        return mapFromApiResponse(
            result = networkBoundResource.fetchData {
                productApiService.getProductsApi()
            },
            mapper = productApiMapper
        )
    }

}