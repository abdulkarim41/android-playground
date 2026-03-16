package com.abdulkarim.architecturepatterns.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkarim.domain.apiusecase.FetchProductsApiUseCase
import com.abdulkarim.entity.ProductApiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.abdulkarim.common.Result
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val fetchProductsApiUseCase: FetchProductsApiUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()


    val action: (UiAction) -> Unit = {
        when(it) {
            is UiAction.FetchProductsApiAction -> fetchProducts()
        }
    }

    init { fetchProducts() }

    fun fetchProducts() {
        viewModelScope.launch {
            fetchProductsApiUseCase.execute().collect { result ->
                when(result){
                    is Result.Loading -> _uiState.value = UiState.Loading
                    is Result.Success -> _uiState.value = UiState.ApiSuccess(result.data)
                    is Result.Error -> _uiState.value = UiState.ApiError(result.message)
                }
            }
        }
    }
}

sealed interface UiState {
    data object Loading : UiState
    data class ApiSuccess(val data: List<ProductApiEntity>) : UiState
    data class ApiError(val message: String) : UiState
}

sealed interface UiAction {
    data object FetchProductsApiAction : UiAction
}