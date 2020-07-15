package com.nennos.pizza.utils

import com.nennos.pizza.api.models.Drink
import com.nennos.pizza.api.models.Ingredient
import com.nennos.pizza.api.models.PizzaData
import com.nennos.pizza.db.AppDatabase
import com.nennos.pizza.db.models.DrinkLocal
import com.nennos.pizza.db.models.DrinkOrderLocal
import com.nennos.pizza.db.models.IngredientLocal
import com.nennos.pizza.db.models.PizzaOrderLocal
import com.nennos.pizza.repository.DatabaseApi
import com.nennos.pizza.ui.adapters.models.PizzaItem

class DbHelper(private val database: AppDatabase) : DatabaseApi {

    override fun savePizzaList(pizzaData: PizzaData) =
        database.pizzaDao().save(pizzaData.convertToLocal())

    override fun getPizzaList() = database.pizzaDao().getAll()

    override fun addPizzaToOrder(pizzaItem: PizzaItem) {
        database.pizzaOrderDao().insert(
            PizzaOrderLocal(
                imageUrl = pizzaItem.url,
                name = pizzaItem.name,
                ingredientIds = pizzaItem.ingredientsId,
                price = pizzaItem.price
            )
        )
    }

    override fun saveIngredientList(ingredients: List<Ingredient>) {
        val ingredientLocalList = mutableListOf<IngredientLocal>()
        ingredients.let {
            for (ingredient in it) {
                ingredientLocalList.add(
                    IngredientLocal(ingredient.id, ingredient.price, ingredient.name)
                )
            }
        }
        database.ingredientDao().save(ingredientLocalList)
    }

    override fun getIngredientList() = database.ingredientDao().getAll()

    override fun saveDrinkList(drinks: List<Drink>) {
        val drinkLocalList = mutableListOf<DrinkLocal>()
        drinks.let {
            for (drink in it) {
                drinkLocalList.add(
                    DrinkLocal(drink.drinkId, drink.price, drink.name)
                )
            }
        }
        database.drinkDao().save(drinkLocalList)
    }

    override fun getDrinkList() = database.drinkDao().getAll()

    override fun getPizzaOrderList() = database.pizzaOrderDao().getAll()

    override fun getDrinkOrderList() = database.drinkOrderDao().getAll()

    override fun addDrinkToOrder(drinkLocal: DrinkLocal) {
        database.drinkOrderDao().insert(
            DrinkOrderLocal(
                drinkId = drinkLocal.drinkId,
                name = drinkLocal.name,
                price = drinkLocal.price
            )
        )
    }

    override fun removePizzaFromOrderList(id: Long) {
        database.pizzaOrderDao().deleteById(id)
    }

    override fun removeDrinkFromOrderList(id: Long) {
        database.drinkOrderDao().deleteById(id)
    }

    override fun removeAllOrders() {
        database.drinkOrderDao().deleteAll()
        database.pizzaOrderDao().deleteAll()
    }
}