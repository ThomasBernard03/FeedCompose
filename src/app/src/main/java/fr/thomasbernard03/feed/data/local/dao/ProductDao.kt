package fr.thomasbernard03.feed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.thomasbernard03.feed.data.local.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(products: List<ProductEntity>)

    @Query("SELECT * FROM ProductEntity WHERE id = :id")
    suspend fun getProduct(id : Int) : ProductEntity
}