package com.mada.ikaly.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mada.ikaly.entities.CategoryItems
import com.mada.ikaly.entities.MealsItems

@Dao
interface RecipeDao {

    @Query("SELECT * FROM CategoryItems ORDER BY id DESC")
    suspend fun getAllCategory() : List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(CategoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealsItems: MealsItems?)

    @Query("DELETE FROM CategoryItems")
    suspend fun clearDb()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    suspend fun getSpecificMealList(categoryName:String) : List<MealsItems>

    @Query("SELECT * FROM MealItems WHERE id=:id")
    suspend fun getMealById(id:Int): MealsItems
}