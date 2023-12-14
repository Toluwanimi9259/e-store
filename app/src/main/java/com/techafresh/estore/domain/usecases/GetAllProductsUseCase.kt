package com.techafresh.estore.domain.usecases

import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.domain.repository.Repository

class GetAllProductsUseCase(private val repository: Repository) {
    suspend fun execute() : List<ProductsItem> = repository.getAllProducts()
}