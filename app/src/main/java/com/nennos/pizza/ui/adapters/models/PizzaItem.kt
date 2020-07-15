package com.nennos.pizza.ui.adapters.models

import com.nennos.pizza.db.models.IngredientLocal
import com.nennos.pizza.db.models.PizzaLocal

class PizzaItem(pizza: PizzaLocal, ingredients: List<IngredientLocal>) {
    var name = pizza.id
    var url = pizza.imageUrl
    val basePrice = pizza.basePrice
    var price = pizza.basePrice + ingredients.sumByDouble { it.price }
    var ingredientsText = ingredients.joinToString { it.name }
    var ingredientsId = ingredients.map { it.id }
}