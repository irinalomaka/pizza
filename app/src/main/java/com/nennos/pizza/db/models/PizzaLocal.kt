package com.nennos.pizza.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PizzaLocal(

    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "image_url")
    var imageUrl: String,

    @ColumnInfo(name = "ingredient_ids")
    var ingredientIds: List<Int>,

    @ColumnInfo(name = "base_price")
    var basePrice: Double

)
