package com.nennos.pizza.ui.adapters.listeners

import com.nennos.pizza.ui.adapters.models.IngredientItem

interface OnIngredientClickedListener {

    fun onIngredientClicked(ingredient: IngredientItem, isAdd: Boolean)
}