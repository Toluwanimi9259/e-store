package com.techafresh.estore.data.impl

import android.util.Log
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.data.retrofit.StoreApi
import com.techafresh.estore.domain.repository.Repository
import java.lang.Exception

class RepositoryImpl(private val storeApi: StoreApi) : Repository {
    override suspend fun getAllProducts(): List<ProductsItem> {
        val response = storeApi.getAllProducts()
        val productz = ArrayList<ProductsItem>()

        try {
            val products = response.body()
            if (products != null) {
                for (i in products.indices){
                    productz.add(ProductsItem(
                        category = products[i].category,
                        description = products[i].description,
                        id = products[i].id,
                        image = products[i].image,
                        price = products[i].price,
                        rating = products[i].rating,
                        title = products[i].title,
                    ))
                }
            }
        }catch (ex : Exception){
            Log.d("MYTAG" , "Repository IMPL")
        }

        return productz
    }
}