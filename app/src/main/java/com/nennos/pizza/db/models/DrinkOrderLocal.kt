package com.nennos.pizza.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrinkOrderLocal(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "drink_id")
    var drinkId: Int,

    var name: String = "",

    var price: Double = 0.0

)