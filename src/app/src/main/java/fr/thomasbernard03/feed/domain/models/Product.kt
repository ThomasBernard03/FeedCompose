package fr.thomasbernard03.feed.domain.models

import fr.thomasbernard03.feed.data.local.entities.ProductEntity

data class Product(
    val id : Int,
    val label : String,
    val description : String,
    val image : String,
    val price : Double?,
    val rating : Double,
){
    constructor(productEntity: ProductEntity) : this(
        id = productEntity.id,
        label = productEntity.label,
        description = productEntity.description,
        image = productEntity.image,
        price = productEntity.price,
        rating = 0.0)
}