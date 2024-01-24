package fr.thomasbernard03.feed.domain.wrappers

import fr.thomasbernard03.feed.data.local.entities.ProductEntity
import fr.thomasbernard03.feed.domain.models.Product

data class ProductWrapper(
    val id : Int,
    val label : String,
    val description : String,
    val image : String,
    val price : Double?,
    val quantity : Int,
)