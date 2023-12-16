package com.techafresh.estore.data.db

import androidx.lifecycle.LiveData
import com.techafresh.estore.data.db.model.CartItem

interface CartInterfaces {
    fun insert(item: CartItem)
    fun delete(item: CartItem)
    fun getAllShoppingItems() : LiveData<List<CartItem>>

    fun insertHistory(historyItem: HistoryItem)

    fun deleteHistory(historyItem: HistoryItem)

    fun deleteAll()

    fun getAllHistory(): LiveData<List<HistoryItem>>

    fun clearCart()
}