package com.techafresh.estore.presentation.di

import com.techafresh.estore.domain.usecases.GetAllProductsUseCase
import com.techafresh.estore.presentation.viewmodel.factory.StoreViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideFactory(getAllProductsUseCase: GetAllProductsUseCase) : StoreViewModelFactory{
        return StoreViewModelFactory(getAllProductsUseCase)
    }
}