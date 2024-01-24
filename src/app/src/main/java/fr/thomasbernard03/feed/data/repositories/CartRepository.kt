package fr.thomasbernard03.feed.data.repositories

interface CartRepository{
    suspend fun addProductToCart(id: Int, quantity: Int)
}