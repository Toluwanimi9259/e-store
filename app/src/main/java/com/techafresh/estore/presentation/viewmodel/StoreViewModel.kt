package com.techafresh.estore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.domain.usecases.GetAllProductsUseCase

class StoreViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    fun getAllProducts()  = liveData{
        emit(getAllProductsUseCase.execute())
    }
}