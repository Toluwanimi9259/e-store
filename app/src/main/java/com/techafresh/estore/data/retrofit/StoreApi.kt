package com.techafresh.estore.data.retrofit

import com.techafresh.estore.data.dataclasses.Products
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

    @GET("/products")
    suspend fun getAllProducts() : Response<Products>
}