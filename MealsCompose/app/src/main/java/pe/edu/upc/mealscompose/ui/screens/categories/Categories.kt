package pe.edu.upc.mealscompose.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pe.edu.upc.mealscompose.data.model.Category
import pe.edu.upc.mealscompose.data.remote.ApiClient
import pe.edu.upc.mealscompose.data.remote.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Categories(selectCategory: (String) -> Unit, modifier: Modifier = Modifier) {

    val categories = remember {
        mutableStateOf(listOf<Category>())
    }

    val categoryService = ApiClient.getCategoryService()

    val fetchCategories = categoryService.fetchCategories()

    fetchCategories.enqueue(object : Callback<CategoryResponse> {
        override fun onResponse(
            call: Call<CategoryResponse>,
            response: Response<CategoryResponse>
        ) {
            if (response.isSuccessful) {
                categories.value = response.body()!!.categories
            }
        }

        override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories.value) {
            CategoryCard(it, selectCategory)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: Category,
    selectCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(4.dp), onClick = {
        selectCategory(category.name)
    }) {
        Column(
            modifier= modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            CategoryImage(category)
            CategoryItem(category)
        }
    }
}

@Composable
fun CategoryImage(category: Category, modifier: Modifier = Modifier) {
    AsyncImage(model = category.url, contentDescription = null)
}

@Composable
fun CategoryItem(category: Category, modifier: Modifier = Modifier) {
    Text(text = category.name)
}