package fr.thomasbernard03.feed.presentation.product

sealed class ProductEvent {
    data class OnGetProduct(val id: Int) : ProductEvent()

    data object OnGoBack : ProductEvent()
}