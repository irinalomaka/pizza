package com.nennos.pizza.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.nennos.pizza.api.models.Drink
import com.nennos.pizza.api.models.Ingredient
import com.nennos.pizza.api.models.PizzaData
import com.nennos.pizza.db.AppDatabase
import com.nennos.pizza.db.models.*
import com.nennos.pizza.ui.adapters.models.PizzaItem
import com.nennos.pizza.utils.DbHelper
import com.nennos.pizza.utils.NetworkHelper
import io.reactivex.Single

class RepositoryImpl(context: Context) : RepositoryApi {

    private val networkManager: NetworkApi = NetworkHelper()
    private val databaseManager: DatabaseApi =
        DbHelper(Room.databaseBuilder(context, AppDatabase::class.java, "database").build())

    override fun fetchPizzasList(): Single<PizzaData> {
        return networkManager.fetchPizzasList()
    }

    override fun fetchDrinksList(): Single<List<Drink>> {
        return networkManager.fetchDrinksList()
    }

    override fun fetchIngredientList(): Single<List<Ingredient>> {
        return networkManager.fetchIngredientList()
    }

    override fun createOrder(drinks: List<Int>, pizzas: List<PizzaOrderLocal>): Single<Any> {
        return networkManager.createOrder(drinks, pizzas)
    }

    override fun savePizzaList(pizzaData: PizzaData) {
        databaseManager.savePizzaList(pizzaData)
    }

    override fun saveDrinkList(drinks: List<Drink>) {
        databaseManager.saveDrinkList(drinks)
    }

    override fun saveIngredientList(ingredients: List<Ingredient>) {
        databaseManager.saveIngredientList(ingredients)
    }

    override fun addPizzaToOrder(pizzaItem: PizzaItem) {
        databaseManager.addPizzaToOrder(pizzaItem)
    }

    override fun addDrinkToOrder(drinkLocal: DrinkLocal) {
        databaseManager.addDrinkToOrder(drinkLocal)
    }

    override fun getPizzaList(): LiveData<List<PizzaLocal>> {
        return databaseManager.getPizzaList()
    }

    override fun getIngredientList(): LiveData<List<IngredientLocal>> {
        return databaseManager.getIngredientList()
    }

    override fun getDrinkList(): LiveData<List<DrinkLocal>> {
        return databaseManager.getDrinkList()
    }

    override fun getPizzaOrderList(): LiveData<List<PizzaOrderLocal>> {
        return databaseManager.getPizzaOrderList()
    }

    override fun getDrinkOrderList(): LiveData<List<DrinkOrderLocal>> {
        return databaseManager.getDrinkOrderList()
    }

    override fun removePizzaFromOrderList(id: Long) {
        databaseManager.removePizzaFromOrderList(id)
    }

    override fun removeDrinkFromOrderList(id: Long) {
        databaseManager.removeDrinkFromOrderList(id)
    }

    override fun removeAllOrders() {
        databaseManager.removeAllOrders()
    }
}