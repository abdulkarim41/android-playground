package com.abdulkarim.architecturepatterns.mvc

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.abdulkarim.architecturepatterns.databinding.FragmentProductMvcBinding
import com.abdulkarim.common.Result
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.domain.apiusecase.FetchProductsApiUseCase
import com.abdulkarim.entity.ProductApiEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductMvcFragment : BaseFragment<FragmentProductMvcBinding>() {

    override fun viewBindingLayout() = FragmentProductMvcBinding.inflate(layoutInflater)

    @Inject
    lateinit var fetchProductsApiUseCase: FetchProductsApiUseCase


    override fun initializeView(savedInstanceState: Bundle?) {
        loadProducts()
    }

    private fun loadProducts() {

        viewLifecycleOwner.lifecycleScope.launch {

            fetchProductsApiUseCase.execute()
                .collect { result ->

                    when (result) {

                        is Result.Loading -> {
                            binding.loadingPB.isVisible = true
                        }

                        is Result.Success -> {
                            binding.loadingPB.isVisible = false
                            showProducts(result.data)
                        }

                        is Result.Error -> {
                            binding.loadingPB.isVisible = false
                            showError(result.message)
                        }
                    }
                }
        }
    }

    private fun showProducts(data: List<ProductApiEntity>) {
        binding.loadingPB.isVisible = false
        binding.resultTV.text = data[0].title
    }

    private fun showError(message: String) {
        binding.loadingPB.isVisible = false
        binding.resultTV.text = message
    }

}