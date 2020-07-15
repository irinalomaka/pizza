package com.nennos.pizza.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientLocal(

    @PrimaryKey
    var id: Int = 0,

    var price: Double = 0.0,

    var name: String = ""
)