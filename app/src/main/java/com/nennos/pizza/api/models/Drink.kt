package com.nennos.pizza.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Drink(
    @SerializedName("id")
    @Expose
    var drinkId: Int = 0,

    @SerializedName("price")
    @Expose
    var price: Double = 0.0,

    @SerializedName("name")
    @Expose
    var name: String = ""
)
