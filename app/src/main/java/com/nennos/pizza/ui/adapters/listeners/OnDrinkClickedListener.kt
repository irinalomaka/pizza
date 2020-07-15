package com.nennos.pizza.ui.adapters.listeners

import com.nennos.pizza.db.models.DrinkLocal

interface OnDrinkClickedListener {

    fun onDrinkClicked(drinkLocal: DrinkLocal)
}