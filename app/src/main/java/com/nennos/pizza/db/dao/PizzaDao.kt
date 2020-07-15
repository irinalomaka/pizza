package com.nennos.pizza.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nennos.pizza.db.models.PizzaLocal

@Dao
interface PizzaDao {
    @Query("SELECT * FROM pizzalocal")
    fun getAll(): LiveData<List<PizzaLocal>>

    @Query("SELECT * FROM pizzalocal WHERE id = :id")
    fun getById(id: Long): PizzaLocal

    @Insert
    fun insert(pizza: PizzaLocal)

    @Update
    fun update(pizza: PizzaLocal)

    @Delete
    fun delete(pizza: PizzaLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pizza: List<PizzaLocal>)
}