package com.abdulkarim.architecturepatterns.mvi

import com.abdulkarim.entity.ProductApiEntity

data class ProductState(
    val isLoading: Boolean = false,

    val products: List<ProductApiEntity> = emptyList(),

    val error: String? = null

)
