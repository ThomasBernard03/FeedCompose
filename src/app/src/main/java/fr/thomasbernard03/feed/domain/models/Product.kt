package fr.thomasbernard03.feed.domain.models

data class Product(
    val id : Int,
    val title : String,
    val description : String,
    val picture : String,
    val price : Double,
    val rating : Double,
)