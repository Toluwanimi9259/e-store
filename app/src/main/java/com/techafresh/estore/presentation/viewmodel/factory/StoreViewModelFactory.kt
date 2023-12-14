package com.techafresh.estore.presentation.viewmodel.factory

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techafresh.estore.domain.usecases.GetAllProductsUseCase
import com.techafresh.estore.presentation.viewmodel.StoreViewModel

class StoreViewModelFactory(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StoreViewModel(
            getAllProductsUseCase
        ) as T
    }
}