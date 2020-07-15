package com.nennos.pizza.ui.adapters.models

import com.nennos.pizza.db.models.IngredientLocal

class IngredientItem(ingredientLocal: IngredientLocal, checked: Boolean) {
    var id = ingredientLocal.id
    var price = ingredientLocal.price
    var name = ingredientLocal.name
    var isChecked = checked
}