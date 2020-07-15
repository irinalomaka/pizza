package com.nennos.pizza.utils

import com.nennos.pizza.api.ApiModule
import com.nennos.pizza.api.models.Drink
import com.nennos.pizza.api.models.Ingredient
import com.nennos.pizza.api.models.PizzaData
import com.nennos.pizza.db.models.*
import com.nennos.pizza.repository.NetworkApi
import io.reactivex.Single

class NetworkHelper : NetworkApi {

    private val baseApiService = ApiModule.provideBaseApiService()
    private val payApiService = ApiModule.providePayApiService()

    override fun fetchPizzasList(): Single<PizzaData> = baseApiService.getPizzas()

    override fun fetchDrinksList(): Single<List<Drink>> = baseApiService.getDrinks()

    override fun fetchIngredientList(): Single<List<Ingredient>> = baseApiService.getIngredients()

    override fun createOrder(drinks: List<Int>, pizzas: List<PizzaOrderLocal>) =
        payApiService.send(drinks.toString(), pizzas.toString())

}