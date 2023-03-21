package com.mada.ikaly.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mada.ikaly.dao.RecipeDao
import com.mada.ikaly.entities.*
import com.mada.ikaly.entities.converter.CategoryListConverter
import com.mada.ikaly.entities.converter.MealListConverter
import com.mada.ikaly.entities.Category
import com.mada.ikaly.entities.CategoryItems
import com.mada.ikaly.entities.Meal
import com.mada.ikaly.entities.Recipes

@Database(entities = [Recipes::class, CategoryItems::class, Category::class, Meal::class,MealsItems::class],version = 1,exportSchema = false)
@TypeConverters(CategoryListConverter::class, MealListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase: RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase {
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                        context,
                        RecipeDatabase::class.java,
                        "ikaly.db"
                ).createFromAsset("database/ikaly.db").build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}