package fr.thomasbernard03.feed.presentation.cart

sealed class CartEvent {
    data object OnGetCart : CartEvent()
}