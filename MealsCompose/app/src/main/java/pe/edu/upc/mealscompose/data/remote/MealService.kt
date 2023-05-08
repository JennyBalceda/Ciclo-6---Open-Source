package pe.edu.upc.mealscompose.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("filter.php")
    fun fetchMealsByCategory(@Query("c") category: String): Call<MealResponse>
}