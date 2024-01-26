package fr.thomasbernard03.feed.presentation.cart

import fr.thomasbernard03.feed.domain.models.Product
import fr.thomasbernard03.feed.domain.wrappers.ProductWrapper

sealed class CartEvent {
    data object OnGetCart : CartEvent()

    data class OnAddProduct(val product: ProductWrapper) : CartEvent()
    data class OnRemoveProduct(val product: ProductWrapper) : CartEvent()
}