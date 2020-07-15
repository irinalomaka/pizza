package com.nennos.pizza.utils

import com.nennos.pizza.api.models.PizzaData
import com.nennos.pizza.db.models.DrinkOrderLocal
import com.nennos.pizza.db.models.IngredientLocal
import com.nennos.pizza.db.models.PizzaLocal
import com.nennos.pizza.db.models.PizzaOrderLocal
import com.nennos.pizza.ui.adapters.models.CartItem
import com.nennos.pizza.ui.adapters.models.IngredientItem

fun PizzaData.convertToLocal(): List<PizzaLocal> {
    val result = mutableListOf<PizzaLocal>()
    for (pizza in this.pizzas ?: emptyList()) {
        result.add(
            PizzaLocal(
                pizza.name,
                pizza.imageUrl ?: "",
                pizza.ingredientIds,
                basePrice
            )
        )
    }
    return result
}

fun List<PizzaOrderLocal>.convertPizzaToCartItem(): List<CartItem> {
    val result = mutableListOf<CartItem>();

    for (pizza in this) {
        result.add(CartItem(pizza.id, 0, pizza.name, pizza.price))
    }

    return result
}

fun List<DrinkOrderLocal>.convertDrinkToCartItem(): List<CartItem> {
    val result = mutableListOf<CartItem>();

    for (drink in this) {
        result.add(CartItem(drink.id, 1, drink.name, drink.price))
    }

    return result
}

fun MutableSet<IngredientItem>.convertToPizzaOrder(): List<Int> {
    val result = mutableListOf<Int>()

    for (ingredient in this) {
        result.add(ingredient.id)
    }
    return result
}

fun List<DrinkOrderLocal>.convertToIds(): List<Int> {
    val result = mutableListOf<Int>()

    for (drink in this) {
        result.add(drink.drinkId)
    }
    return result
}

fun MutableSet<IngredientItem>.convertToIngredientLocal(): List<IngredientLocal> {
    val result = mutableListOf<IngredientLocal>()

    for (ingredient in this) {
        result.add(IngredientLocal(ingredient.id, ingredient.price, ingredient.name))
    }
    return result
}
