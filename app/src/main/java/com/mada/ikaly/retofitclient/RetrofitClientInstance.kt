package com.mada.ikaly.retofitclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClientInstance {
    companion object{
        val BASE_URL = "https://base_de_donnee_ikaly"
        private var retrofit: Retrofit? = null
        val retrofitInstance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }

}