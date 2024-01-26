package fr.thomasbernard03.feed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.thomasbernard03.feed.data.local.entities.CartEntity
import fr.thomasbernard03.feed.data.local.entities.ProductWithQuantity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(cart: CartEntity)

    @Query("SELECT quantity FROM CartEntity WHERE productId = :productId")
    suspend fun getCartQuantity(productId : Int): Int

    @Query("""
        SELECT ProductEntity.*, CartEntity.quantity 
        FROM ProductEntity 
        INNER JOIN CartEntity ON ProductEntity.id = CartEntity.productId
        WHERE CartEntity.quantity > 0
    """)
    fun getProductsWithQuantity(): List<ProductWithQuantity>


}