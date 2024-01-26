package fr.thomasbernard03.feed.presentation.home

import fr.thomasbernard03.feed.domain.models.Menu
import fr.thomasbernard03.feed.domain.models.Product

data class HomeUiState(
    val featuredMenus: List<Menu>? = null,
    val products : List<Product>? = null,

    val query : String = "",
)