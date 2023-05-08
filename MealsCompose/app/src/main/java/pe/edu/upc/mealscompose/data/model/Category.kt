package pe.edu.upc.mealscompose.data.model

import com.google.gson.annotations.SerializedName

data class Category(

    @SerializedName("idCategory")
    val id: String,

    @SerializedName("strCategory")
    val name: String,

    @SerializedName("strCategoryThumb")
    val url: String
)
