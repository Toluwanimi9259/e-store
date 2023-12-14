package com.techafresh.estore.domain.repository

import com.techafresh.estore.data.dataclasses.ProductsItem

interface Repository {
    suspend fun getAllProducts() : List<ProductsItem>
}