package com.abdulkarim.architecturepatterns.mvp

import com.abdulkarim.entity.ProductApiEntity
import com.abdulkarim.common.Result

interface ProductContract {

    interface View {
        fun render(result: Result<List<ProductApiEntity>>)
    }

    interface Presenter {
        fun loadProducts()
        fun onDestroy()
    }
}