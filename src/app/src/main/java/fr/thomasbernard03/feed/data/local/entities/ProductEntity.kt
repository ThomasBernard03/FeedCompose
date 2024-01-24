package fr.thomasbernard03.feed.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val label : String,
    val image : String,
    val description : String,
)