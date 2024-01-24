package fr.thomasbernard03.feed.presentation.home

import fr.thomasbernard03.feed.domain.models.Product

sealed class HomeEvent {
    data object OnGetProducts : HomeEvent()

    data class OnProductClicked(val product: Product) : HomeEvent()
}