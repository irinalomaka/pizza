package com.nennos.pizza.api.models

import com.google.gson.annotations.SerializedName

data class PizzaData(
    @field:SerializedName("pizzas")
    val pizzas: List<Pizza>? = null,

    @field:SerializedName("basePrice")
    val basePrice: Double = 0.0
)