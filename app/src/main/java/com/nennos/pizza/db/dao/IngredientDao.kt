package com.nennos.pizza.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nennos.pizza.db.models.IngredientLocal

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredientlocal")
    fun getAll(): LiveData<List<IngredientLocal>>

    @Query("SELECT * FROM ingredientlocal WHERE id = :id")
    fun getById(id: Int): IngredientLocal

    @Insert
    fun insert(ingredient: IngredientLocal)

    @Update
    fun update(ingredient: IngredientLocal)

    @Delete
    fun delete(ingredient: IngredientLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(drink: List<IngredientLocal>)
}