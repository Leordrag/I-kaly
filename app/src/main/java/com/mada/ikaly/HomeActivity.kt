package com.mada.ikaly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mada.ikaly.R
import com.mada.ikaly.adapter.MainCategoryAdapter
import com.mada.ikaly.adapter.SubCategoryAdapter
import com.mada.ikaly.database.RecipeDatabase
import com.mada.ikaly.entities.CategoryItems
import com.mada.ikaly.entities.MealsItems
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDb()

        mainCategoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)


    }

    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            var intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                getMealDataFromDb(arrMainCategory[0].strcategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter = mainCategoryAdapter
                mainCategoryAdapter.setData(arrMainCategory)
            }

        }
    }

    private fun getMealDataFromDb(categoryName:String){
        tvCategory.text = "Categories: $categoryName"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                rv_sub_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_sub_category.adapter = subCategoryAdapter
                subCategoryAdapter.setData(arrSubCategory)
            }


        }
    }
}