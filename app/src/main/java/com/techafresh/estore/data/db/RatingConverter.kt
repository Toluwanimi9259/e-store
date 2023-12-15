package com.techafresh.estore.data.db

import androidx.room.TypeConverter
import com.techafresh.estore.data.dataclasses.Rating

class RatingConverter {
    @TypeConverter
    fun fromSource(rating : Rating) : Int{
        return 0
    }

    @TypeConverter
    fun toSource(rating : Int) : Rating{
        return Rating(0,0.0)
    }
}