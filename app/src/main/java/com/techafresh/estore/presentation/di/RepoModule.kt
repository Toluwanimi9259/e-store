package com.techafresh.estore.presentation.di

import com.techafresh.estore.data.impl.RepositoryImpl
import com.techafresh.estore.data.retrofit.StoreApi
import com.techafresh.estore.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideRepo(storeApi: StoreApi) : Repository{
        return RepositoryImpl(storeApi)
    }
}