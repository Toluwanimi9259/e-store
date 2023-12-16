package com.techafresh.estore.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.techafresh.estore.data.dataclasses.Rating
import java.util.Date

@Entity(tableName = "history")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val category: String,
    val description: String,
    val product_id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String,
    var quantity : Int,

    @ColumnInfo(name = "date" , defaultValue = "Problem")
    var orderDate : String

) {
}