package com.nennos.pizza.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ingredient(
    @field:SerializedName("price")
    @Expose
    val price: Double = 0.0,

    @field:SerializedName("name")
    @Expose
    val name: String = "",

    @field:SerializedName("id")
    @Expose
    val id: Int = 0
)