package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.ProductApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProductsApi() : Response<ProductApiResponse>
}