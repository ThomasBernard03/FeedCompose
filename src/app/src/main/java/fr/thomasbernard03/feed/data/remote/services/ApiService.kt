package fr.thomasbernard03.feed.data.remote.services

import fr.thomasbernard03.feed.data.remote.dto.ProductDto

interface ApiService {

    suspend fun getProducts(): List<ProductDto>
}