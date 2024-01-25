package fr.thomasbernard03.feed.presentation.cart

import fr.thomasbernard03.feed.domain.wrappers.ProductWrapper

data class CartUiState(
    val loading : Boolean = false,

    val products : List<ProductWrapper>? = null,
)