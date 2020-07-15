package com.nennos.pizza.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nennos.pizza.db.models.DrinkLocal


@Dao
interface DrinkDao {

    @Query("SELECT * FROM drinklocal")
    fun getAll(): LiveData<List<DrinkLocal>>

    @Query("SELECT * FROM drinklocal WHERE drinkId = :id")
    fun getById(id: Int): DrinkLocal

    @Insert
    fun insert(drink: DrinkLocal)

    @Update
    fun update(drink: DrinkLocal)

    @Delete
    fun delete(drink: DrinkLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(drink: List<DrinkLocal>)
}