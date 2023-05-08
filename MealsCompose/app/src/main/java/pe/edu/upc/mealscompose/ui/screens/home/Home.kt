package pe.edu.upc.mealscompose.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.mealscompose.ui.screens.categories.Categories
import pe.edu.upc.mealscompose.ui.screens.meals.Meals

@Composable
fun Home() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            Categories(selectCategory = {
                navController.navigate("meals/$it")
            })
        }
        composable(
            "meals/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            Meals(category!!)
        }

    }
}