package com.abdulkarim.architecturepatterns.mvi

sealed class ProductIntent {
    object LoadProducts : ProductIntent()
}