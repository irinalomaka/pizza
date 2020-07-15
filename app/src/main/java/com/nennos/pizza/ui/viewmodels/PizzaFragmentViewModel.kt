package com.nennos.pizza.ui.viewmodels

import android.app.Application
import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nennos.pizza.db.models.PizzaLocal
import com.nennos.pizza.ui.adapters.models.IngredientItem
import com.nennos.pizza.ui.adapters.models.PizzaItem
import com.nennos.pizza.utils.convertToIngredientLocal
import com.nennos.pizza.utils.convertToPizzaOrder

class PizzaFragmentViewModel(application: Application) : BaseViewModel(application) {

    private val handler = Handler()

    private val addedIngredients = mutableSetOf<IngredientItem>()
    private val ingredientLocalList = repository.getIngredientList()
    private var preparedIngredientsList = ArrayList<Int>()

    private var basePrice = 0.0
    private var pizzaId = ""
    var imageUrl = ""

    val addedCustomPizzaToCartView = MutableLiveData<Boolean>()
    val totalPrice = MutableLiveData<Double>()

    var ingredientList = Transformations.map(ingredientLocalList) {
        val result = mutableListOf<IngredientItem>()

        for (item in it) {
            val isAdded = preparedIngredientsList.contains(item.id)

            if (isAdded) {
                addedIngredients.add(IngredientItem(item, isAdded))
                recalculateTotalPrice()
            }

            result.add(IngredientItem(item, isAdded))
        }

        result
    }

    fun updateIngredients(ingredient: IngredientItem, isAdd: Boolean) {
        when (isAdd) {
            true -> addedIngredients.add(ingredient)
            false -> addedIngredients.remove(ingredient)
        }

        recalculateTotalPrice()
    }

    fun setPizzaData(id: String, price: Double, url: String, ingredients: ArrayList<Int>) {
        pizzaId = id
        imageUrl = url
        basePrice = price

        preparedIngredientsList.clear()
        preparedIngredientsList.addAll(ingredients)

        recalculateTotalPrice()
    }

    private fun recalculateTotalPrice() {
        totalPrice.value = basePrice + addedIngredients.sumByDouble { it.price }
    }

    fun addPizza() {
        handler.removeCallbacksAndMessages(null)
        addedCustomPizzaToCartView.value = true
        handler.postDelayed({ addedCustomPizzaToCartView.value = false }, DELAY)
        execute(Runnable {
            repository.addPizzaToOrder(
                PizzaItem(
                    PizzaLocal(
                        pizzaId,
                        imageUrl,
                        addedIngredients.convertToPizzaOrder(),
                        basePrice
                    ),
                    addedIngredients.convertToIngredientLocal().toList()
                )
            )
        })
    }

    override fun onCleared() {
        handler.removeCallbacksAndMessages(null)
        super.onCleared()
    }
}