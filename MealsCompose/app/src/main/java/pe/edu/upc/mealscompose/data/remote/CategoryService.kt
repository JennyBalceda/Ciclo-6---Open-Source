package pe.edu.upc.mealscompose.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {

    @GET("categories.php")
    fun fetchCategories(): Call<CategoryResponse>
}