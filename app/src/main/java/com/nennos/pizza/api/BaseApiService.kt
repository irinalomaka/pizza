package com.nennos.pizza.api

import com.nennos.pizza.api.models.Drink
import com.nennos.pizza.api.models.Ingredient
import com.nennos.pizza.api.models.PizzaData
import io.reactivex.Single
import retrofit2.http.GET

interface BaseApiService {

    @GET("ozt3z")
    fun getIngredients(): Single<List<Ingredient>>

    @GET("150da7")
    fun getDrinks(): Single<List<Drink>>


    @GET("dokm7")
    fun getPizzas(): Single<PizzaData>
}