package com.nennos.pizza.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nennos.pizza.db.converters.Converters
import com.nennos.pizza.db.dao.*
import com.nennos.pizza.db.models.*

@Database(
    entities = [DrinkLocal::class, IngredientLocal::class, PizzaLocal::class, DrinkOrderLocal::class, PizzaOrderLocal::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao
    abstract fun pizzaDao(): PizzaDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun pizzaOrderDao(): PizzaOrderDao
    abstract fun drinkOrderDao(): DrinkOrderDao
}