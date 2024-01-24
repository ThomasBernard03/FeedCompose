package fr.thomasbernard03.feed.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import fr.thomasbernard03.feed.data.local.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getAll(): List<ProductEntity>
}