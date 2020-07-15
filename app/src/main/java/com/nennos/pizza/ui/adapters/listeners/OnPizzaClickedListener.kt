package com.nennos.pizza.ui.adapters.listeners

import com.nennos.pizza.ui.adapters.models.PizzaItem

interface OnPizzaClickedListener {

    fun onPizzaAddClicked(pizzaItem: PizzaItem)

    fun onPizzaEditClicked(pizzaItem: PizzaItem)
}