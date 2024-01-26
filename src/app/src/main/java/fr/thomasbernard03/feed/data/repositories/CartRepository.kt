package fr.thomasbernard03.feed.data.repositories

import fr.thomasbernard03.feed.data.local.entities.ProductWithQuantity
import fr.thomasbernard03.feed.domain.models.Product

interface CartRepository{
    suspend fun addProductToCart(id: Int, quantity: Int)
    suspend fun addProductToCart(id: Int)
    suspend fun removeProductToCart(id: Int)

    suspend fun getQuantityOfProduct(id: Int): Int
    suspend fun updateQuantityOfProduct(id: Int, quantity: Int)

    suspend fun getCartProductsWithQuantity(): List<ProductWithQuantity>
}