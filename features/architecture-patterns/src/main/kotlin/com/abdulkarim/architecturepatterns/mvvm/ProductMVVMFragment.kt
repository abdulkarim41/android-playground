package com.abdulkarim.architecturepatterns.mvvm

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.abdulkarim.architecturepatterns.databinding.FragmentProductMvvmBinding
import com.abdulkarim.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class ProductMVVMFragment : BaseFragment<FragmentProductMvvmBinding>() {

    override fun viewBindingLayout() = FragmentProductMvvmBinding.inflate(layoutInflater)
    private val viewModel by viewModels<ProductViewModel>()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.uiState bindTo :: handleUiState

    }

    private fun handleUiState(state: ProductUiState) {
        when (state) {
            is ProductUiState.Loading -> {
                binding.loadingPB.isVisible = true
            }
            is ProductUiState.ApiSuccess -> {
                binding.loadingPB.isVisible = false
                binding.resultTV.text = state.data[0].title
            }
            is ProductUiState.ApiError -> {
                binding.resultTV.text = state.message
            }
        }
    }

}