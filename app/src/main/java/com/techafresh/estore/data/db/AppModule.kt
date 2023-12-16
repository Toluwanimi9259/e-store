package com.techafresh.estore.data.db

import android.content.Context
import androidx.room.Room
import com.techafresh.estore.data.db.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingDatabase(@ApplicationContext appContext: Context): CartDatabase {
        return Room.databaseBuilder(
            appContext,
            CartDatabase::class.java,
            "RssReader"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideShoppingDao(shoppingDatabase: CartDatabase): CartDao {
        return shoppingDatabase.getCartDao()
    }

    @Provides
    fun provideShoppingRepository(db: CartDatabase): CartInterfaces = CartRepository(db)



}