package com.nennos.pizza.repository

import androidx.lifecycle.LiveData
import com.nennos.pizza.api.models.Drink
import com.nennos.pizza.api.models.Ingredient
import com.nennos.pizza.api.models.PizzaData
import com.nennos.pizza.db.models.*
import com.nennos.pizza.ui.adapters.models.PizzaItem

interface DatabaseApi {

    //INSERT or UPDATE operations
    fun savePizzaList(pizzaData: PizzaData)

    fun saveDrinkList(drinks: List<Drink>)

    fun saveIngredientList(ingredients: List<Ingredient>)

    fun addPizzaToOrder(pizzaItem: PizzaItem)

    fun addDrinkToOrder(drinkLocal: DrinkLocal)

    fun getPizzaList(): LiveData<List<PizzaLocal>>

    //SELECT OPERATIONS
    fun getIngredientList(): LiveData<List<IngredientLocal>>

    fun getDrinkList(): LiveData<List<DrinkLocal>>

    fun getPizzaOrderList(): LiveData<List<PizzaOrderLocal>>

    fun getDrinkOrderList(): LiveData<List<DrinkOrderLocal>>

    //DELETE operations
    fun removePizzaFromOrderList(id: Long)

    fun removeDrinkFromOrderList(id: Long)

    fun removeAllOrders()
}