package com.abdulkarim.architecturepatterns.mvvm

import com.abdulkarim.domain.apiusecase.FetchProductsApiUseCase
import com.abdulkarim.entity.ProductApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.abdulkarim.common.Result
import com.abdulkarim.common.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val fetchProductsApiUseCase: FetchProductsApiUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState = _uiState.asStateFlow()


    val action: (ProductUiAction) -> Unit = {
        when(it) {
            is ProductUiAction.FetchProductsApiAction -> fetchProducts()
        }
    }

    init { fetchProducts() }

    fun fetchProducts() {
        execute {
            fetchProductsApiUseCase.execute().collect { result ->
                when(result){
                    is Result.Loading -> _uiState.value = ProductUiState.Loading
                    is Result.Success -> {
                        _uiState.value = ProductUiState.ApiSuccess(result.data)
                        Timber.d("KKK ${result.data}")
                    }
                    is Result.Error -> _uiState.value = ProductUiState.ApiError(result.message)
                }
            }
        }
    }
}

sealed interface ProductUiState {
    data object Loading : ProductUiState
    data class ApiSuccess(val data: List<ProductApiEntity>) : ProductUiState
    data class ApiError(val message: String) : ProductUiState
}

sealed interface ProductUiAction {
    data object FetchProductsApiAction : ProductUiAction
}