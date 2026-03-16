package com.abdulkarim.architecturepatterns.mvi

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.abdulkarim.architecturepatterns.databinding.FragmentProductMviBinding
import com.abdulkarim.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductMVIFragment : BaseFragment<FragmentProductMviBinding>() {

    override fun viewBindingLayout() = FragmentProductMviBinding.inflate(layoutInflater)

    private val viewModel: ProductViewModel by viewModels()

    override fun initializeView(savedInstanceState: Bundle?) {

        viewModel.handleIntent(ProductIntent.LoadProducts)
        observeState()

    }

    private fun observeState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.state.collect { state ->

                binding.loadingPB.isVisible = state.isLoading

                state.products.firstOrNull()?.let {
                    binding.resultTV.text = it.title
                }

                state.error?.let {
                    binding.resultTV.text = it
                }
            }
        }
    }

}