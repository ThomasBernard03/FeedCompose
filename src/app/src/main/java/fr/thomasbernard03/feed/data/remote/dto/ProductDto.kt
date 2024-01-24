package fr.thomasbernard03.feed.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id : Int,

    @SerializedName("label")
    val label : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("image")
    val image : String,

    @SerializedName("price")
    val price : Double,
)