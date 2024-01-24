package fr.thomasbernard03.feed.presentation.product

import fr.thomasbernard03.feed.domain.models.Product
import fr.thomasbernard03.feed.domain.wrappers.ProductWrapper

data class ProductUiState(
    val product : ProductWrapper? = null,

    val quantity : Int = 0
)