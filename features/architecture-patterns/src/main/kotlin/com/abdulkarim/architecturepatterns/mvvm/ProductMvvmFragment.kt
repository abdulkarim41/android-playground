package com.abdulkarim.architecturepatterns.mvvm

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.abdulkarim.architecturepatterns.databinding.FragmentProductBinding
import com.abdulkarim.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.getValue

@AndroidEntryPoint
class ProductMvvmFragment : BaseFragment<FragmentProductBinding>() {

    override fun viewBindingLayout() = FragmentProductBinding.inflate(layoutInflater)
    private val viewModel by viewModels<ProductViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiState bindTo :: handleUiState

    }

    private fun handleUiState(state: ProductUiState) {
        when (state) {
            is ProductUiState.Loading -> {
                Timber.d("KKK Loading")
            }
            is ProductUiState.ApiSuccess -> {
                Timber.d("KKK Success : ${state.data}")
            }
            is ProductUiState.ApiError -> {
                Timber.d("KKK Error : ${state.message}")
            }
        }
    }

}