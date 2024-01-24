package fr.thomasbernard03.feed.presentation.product

import fr.thomasbernard03.feed.domain.models.Product

data class ProductUiState(
    val product : Product? = null,
)