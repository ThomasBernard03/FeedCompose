package fr.thomasbernard03.feed.presentation.product

import fr.thomasbernard03.feed.domain.models.Product

sealed class ProductEvent {
    data class OnGetProduct(val id: Int) : ProductEvent()

    data object OnGoBack : ProductEvent()

    data class OnAddToCart(val product: Product) : ProductEvent()
}