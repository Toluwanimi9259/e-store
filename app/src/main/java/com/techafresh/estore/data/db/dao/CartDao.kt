package com.techafresh.estore.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techafresh.estore.data.db.HistoryItem
import com.techafresh.estore.data.db.model.CartItem

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CartItem)

    @Delete
    fun delete(item: CartItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<CartItem>>

    @Query("DELETE FROM shopping_items")
    fun clearCart()

    // History
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertHistory(historyItem: HistoryItem)

    @Delete
    fun deleteHistory(historyItem: HistoryItem)

    @Query("Delete FROM history")
    fun deleteAll()

    @Query("SELECT * FROM history")
    fun getAllHistory(): LiveData<List<HistoryItem>>
}