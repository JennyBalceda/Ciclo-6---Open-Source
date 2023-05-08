package pe.edu.upc.mealscompose.data.remote

import pe.edu.upc.mealscompose.data.model.Meal

data class MealResponse(
    val meals: List<Meal>
)
