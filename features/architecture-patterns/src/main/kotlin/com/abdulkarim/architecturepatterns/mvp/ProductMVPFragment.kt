package com.abdulkarim.architecturepatterns.mvp

import android.os.Bundle
import androidx.core.view.isVisible
import com.abdulkarim.architecturepatterns.databinding.FragmentProductMvpBinding
import com.abdulkarim.common.Result
import com.abdulkarim.common.base.BaseFragment
import com.abdulkarim.entity.ProductApiEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductMVPFragment : BaseFragment<FragmentProductMvpBinding>(), ProductContract.View{

    override fun viewBindingLayout() = FragmentProductMvpBinding.inflate(layoutInflater)

    @Inject
    lateinit var presenter: ProductPresenter

    override fun initializeView(savedInstanceState: Bundle?) {
        presenter.attachView(this)
        presenter.loadProducts()
    }

    override fun render(result: Result<List<ProductApiEntity>>) {
        when (result) {
            is Result.Loading -> {
                binding.loadingPB.isVisible = true
            }

            is Result.Success -> {
                binding.loadingPB.isVisible = false
                binding.resultTV.text = result.data[0].title
            }

            is Result.Error -> {
                binding.loadingPB.isVisible = false
                binding.resultTV.text = result.message
            }
        }
    }

}