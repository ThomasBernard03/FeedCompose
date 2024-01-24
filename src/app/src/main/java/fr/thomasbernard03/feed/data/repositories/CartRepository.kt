package fr.thomasbernard03.feed.data.repositories

interface CartRepository{
    suspend fun addProductToCart(id: Int, quantity: Int)
    suspend fun getQuantityOfProduct(id: Int): Int
    suspend fun updateQuantityOfProduct(id: Int, quantity: Int)
}