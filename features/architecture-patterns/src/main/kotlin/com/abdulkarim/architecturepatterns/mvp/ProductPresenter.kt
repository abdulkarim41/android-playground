package com.abdulkarim.architecturepatterns.mvp

import com.abdulkarim.domain.apiusecase.FetchProductsApiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductPresenter @Inject constructor(
    private val useCase: FetchProductsApiUseCase
) : ProductContract.Presenter {

    private var view: ProductContract.View? = null
    private var job: Job? = null

    fun attachView(view: ProductContract.View) {
        this.view = view
    }

    override fun loadProducts() {

        job = CoroutineScope(Dispatchers.Main).launch {

            useCase.execute().collect { result ->
                view?.render(result)
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        view = null
    }
}