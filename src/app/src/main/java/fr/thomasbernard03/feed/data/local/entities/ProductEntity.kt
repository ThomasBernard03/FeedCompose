package fr.thomasbernard03.feed.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.thomasbernard03.feed.data.remote.dto.ProductDto

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val label : String,
    val image : String,
    val description : String,
    val price : Double?,
){
    constructor(dto : ProductDto) : this(
        id = dto.id,
        label = dto.label,
        image = dto.image,
        description = dto.description,
        price = dto.price)
}