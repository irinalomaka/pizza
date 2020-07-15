package com.nennos.pizza.repository

import com.nennos.pizza.api.models.Drink
import com.nennos.pizza.api.models.Ingredient
import com.nennos.pizza.api.models.PizzaData
import com.nennos.pizza.db.models.PizzaOrderLocal
import io.reactivex.Single

interface NetworkApi {

    //Network Methods
    fun fetchPizzasList(): Single<PizzaData>

    fun fetchDrinksList(): Single<List<Drink>>

    fun fetchIngredientList(): Single<List<Ingredient>>

    fun createOrder(drinks: List<Int>, pizzas: List<PizzaOrderLocal>): Single<Any>
}