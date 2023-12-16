package com.techafresh.estore.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.techafresh.estore.data.db.dao.CartDao
import com.techafresh.estore.data.db.model.CartItem

@Database(
    entities = [CartItem::class,HistoryItem::class],
    version = 2
)
@TypeConverters(RatingConverter::class)
abstract class CartDatabase: RoomDatabase() {

    abstract fun getCartDao(): CartDao

    companion object {
        @Volatile
        private var instance: CartDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CartDatabase::class.java, "cart.db").fallbackToDestructiveMigration().build()
    }
}