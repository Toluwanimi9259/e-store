package com.techafresh.estore.data.db

import androidx.lifecycle.LiveData
import com.techafresh.estore.data.db.model.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val db: CartDatabase
): CartInterfaces {
    override fun insert(item: CartItem) = db.getCartDao().insert(item)

    override fun delete(item: CartItem) = db.getCartDao().delete(item)

    override fun getAllShoppingItems() = db.getCartDao().getAllShoppingItems()

    override fun insertHistory(historyItem: HistoryItem) = db.getCartDao().insertHistory(historyItem)
    override fun deleteHistory(historyItem: HistoryItem) = db.getCartDao().deleteHistory(historyItem)

    override fun deleteAll() = db.getCartDao().deleteAll()

    override fun getAllHistory(): LiveData<List<HistoryItem>> = db.getCartDao().getAllHistory()
    override fun clearCart() = db.getCartDao().clearCart()


}