package com.mada.ikaly


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mada.ikaly.R
import com.mada.ikaly.database.RecipeDatabase
import com.mada.ikaly.entities.MealResponse
import com.mada.ikaly.entities.MealsItems
import com.mada.ikaly.interfaces.GetDataService
import com.mada.ikaly.retofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.tvCategory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")?.toInt()
        getItem(id!!)
//        getSpecificItem(id!!)

        imgToolbarBtnBack.setOnClickListener {
            finish()
        }

        btnYoutube.setOnClickListener {
            Toast.makeText(this@DetailActivity,"Commande pas encore disponible: il s'agit d'un prototype",Toast.LENGTH_SHORT).show()
        }

    }

    fun getItem(id:Int){
        launch {
            this.let {
                val cat = RecipeDatabase.getDatabase(this@DetailActivity).recipeDao().getMealById(id)

                Glide.with(this@DetailActivity).load("file:///android_asset/images/"+cat.strMealThumb).into(imgItem)
                tvCategory.text = cat.categoryName+": "+cat.strMeal

            }

        }
    }



}