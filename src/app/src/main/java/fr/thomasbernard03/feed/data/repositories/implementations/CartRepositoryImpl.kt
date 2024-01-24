package fr.thomasbernard03.feed.data.repositories.implementations

import fr.thomasbernard03.feed.data.local.dao.CartDao
import fr.thomasbernard03.feed.data.local.entities.CartEntity
import fr.thomasbernard03.feed.data.repositories.CartRepository
import org.koin.java.KoinJavaComponent.get

class CartRepositoryImpl(
    private val cartDao: CartDao = get(CartDao::class.java)
) : CartRepository {

    override suspend fun addProductToCart(id: Int, quantity: Int) {
        // If the product is already in the cart, we update the quantity
        val existingUQuantity = cartDao.getCartQuantity(id)
        val cart = CartEntity(id, existingUQuantity + quantity)
        cartDao.insertOrUpdate(cart)
    }
}