package pe.edu.upc.mealscompose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val URL_BASE = "https://www.themealdb.com/api/json/v1/1/"

    fun getCategoryService(): CategoryService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CategoryService::class.java)
    }

    fun getMealService(): MealService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MealService::class.java)
    }
}