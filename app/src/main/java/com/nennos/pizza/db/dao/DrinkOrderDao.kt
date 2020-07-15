package com.nennos.pizza.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nennos.pizza.db.models.DrinkOrderLocal

@Dao
interface DrinkOrderDao {
    @Query("SELECT * FROM drinkorderlocal")
    fun getAll(): LiveData<List<DrinkOrderLocal>>

    @Query("SELECT * FROM drinkorderlocal WHERE id = :id")
    fun getById(id: Long): DrinkOrderLocal

    @Insert
    fun insert(drinkOrderDao: DrinkOrderLocal)

    @Update
    fun update(drinkOrderDao: DrinkOrderLocal)

    @Delete
    fun delete(drinkOrderDao: DrinkOrderLocal)

    @Query("DELETE FROM drinkorderlocal WHERE id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM drinkorderlocal")
    fun deleteAll()

}