package com.nennos.pizza.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PizzaOrderLocal(

    @ColumnInfo(name = "image_url") var imageUrl: String,
    var name: String,

    @ColumnInfo(name = "ingredient_ids") var ingredientIds: List<Int>,
    var price: Double

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}