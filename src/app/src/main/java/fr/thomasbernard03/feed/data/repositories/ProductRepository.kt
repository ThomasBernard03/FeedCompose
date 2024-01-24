package fr.thomasbernard03.feed.data.repositories

import fr.thomasbernard03.feed.data.local.entities.ProductEntity

interface ProductRepository {
    suspend fun getProducts() : List<ProductEntity>
    suspend fun getProduct(id : Int) : ProductEntity
}