package fr.thomasbernard03.feed.presentation.home

sealed class HomeEvent {
    data object OnGetFeaturedMenu : HomeEvent()
    data object OnGetProducts : HomeEvent()
}