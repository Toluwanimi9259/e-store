package com.techafresh.estore.presentation.di

import com.squareup.picasso.BuildConfig
import com.techafresh.estore.data.retrofit.StoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()
    }

    @Singleton
    @Provides
    fun provideStoreApi(retrofit: Retrofit) : StoreApi{
        return retrofit.create(StoreApi::class.java)
    }
}