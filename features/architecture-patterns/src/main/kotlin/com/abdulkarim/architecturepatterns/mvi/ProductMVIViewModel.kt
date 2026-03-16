package com.abdulkarim.architecturepatterns.mvi

import com.abdulkarim.domain.apiusecase.FetchProductsApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.abdulkarim.common.Result
import com.abdulkarim.common.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val fetchProductsApiUseCase: FetchProductsApiUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state

    fun handleIntent(intent: ProductIntent) {

        when (intent) {

            is ProductIntent.LoadProducts -> {
                loadProducts()
            }
        }
    }

    private fun loadProducts() {
        execute {
            fetchProductsApiUseCase.execute().collect { result ->

                when (result) {

                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            products = result.data,
                            error = null
                        )
                    }

                    is Result.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}