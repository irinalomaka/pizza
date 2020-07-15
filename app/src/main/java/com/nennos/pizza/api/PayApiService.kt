package com.nennos.pizza.api

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface PayApiService {

    @FormUrlEncoded
    @POST("post")
    fun send(@Field(value = "drinks")  drinksList: String,
             @Field(value = "pizzas")  pizzasList: String) : Single<Any>

}

