package com.techafresh.estore.presentation.di

import com.techafresh.estore.domain.repository.Repository
import com.techafresh.estore.domain.usecases.GetAllProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(repository: Repository) : GetAllProductsUseCase{
        return GetAllProductsUseCase(repository)
    }
}