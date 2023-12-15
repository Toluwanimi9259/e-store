package com.techafresh.estore.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.techafresh.estore.data.dataclasses.Rating

@Entity(tableName = "shopping_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val category: String,
    val description: String,
    val product_id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String,
    var quantity : Int
    ) {
}