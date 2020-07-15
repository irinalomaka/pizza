package com.nennos.pizza.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nennos.pizza.db.models.PizzaOrderLocal

@Dao
interface PizzaOrderDao {
    @Query("SELECT * FROM pizzaorderlocal")
    fun getAll(): LiveData<List<PizzaOrderLocal>>

    @Query("SELECT * FROM pizzaorderlocal WHERE id = :id")
    fun getById(id: Long): PizzaOrderLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderLocal: PizzaOrderLocal)

    @Update
    fun update(orderLocal: PizzaOrderLocal)

    @Delete
    fun delete(orderLocal: PizzaOrderLocal)

    @Query("DELETE FROM pizzaorderlocal WHERE id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM pizzaorderlocal")
    fun deleteAll()

}