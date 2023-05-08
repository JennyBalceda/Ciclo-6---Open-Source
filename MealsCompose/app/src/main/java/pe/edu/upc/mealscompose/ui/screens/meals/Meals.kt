package pe.edu.upc.mealscompose.ui.screens.meals

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pe.edu.upc.mealscompose.data.model.Category
import pe.edu.upc.mealscompose.data.model.Meal
import pe.edu.upc.mealscompose.data.remote.ApiClient
import pe.edu.upc.mealscompose.data.remote.CategoryResponse
import pe.edu.upc.mealscompose.data.remote.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Meals(category: String, modifier: Modifier = Modifier) {

    val meals = remember {
        mutableStateOf(listOf<Meal>())
    }

    val categoryService = ApiClient.getMealService()

    val fetchMealsByCategory = categoryService.fetchMealsByCategory(category)

    fetchMealsByCategory.enqueue(object : Callback<MealResponse> {
        override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
            if (response.isSuccessful) {
                meals.value = response.body()!!.meals
            }
        }

        override fun onFailure(call: Call<MealResponse>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })


    LazyColumn(modifier = modifier) {
        items(meals.value) {
            MealCard(it)
        }
    }
}

@Composable
fun MealCard(meal: Meal, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            MealImage(meal)
            Spacer(modifier = modifier.width(4.dp))
            MealItem(meal)
        }
    }
}

@Composable
fun MealImage(meal: Meal, modifier: Modifier = Modifier) {
    AsyncImage(model = meal.url, contentDescription = null, modifier = modifier.size(92.dp))
}

@Composable
fun MealItem(meal: Meal, modifier: Modifier = Modifier) {
    Text(text = meal.name)
}