package com.techafresh.estore.data.db

import com.techafresh.estore.data.db.model.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val db: CartDatabase
): CartInterfaces {
    override fun insert(item: CartItem) = db.getCartDao().insert(item)

    override fun delete(item: CartItem) = db.getCartDao().delete(item)

    override fun getAllShoppingItems() = db.getCartDao().getAllShoppingItems()
}