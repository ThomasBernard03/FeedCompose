package fr.thomasbernard03.feed.domain.usecases

import fr.thomasbernard03.feed.data.repositories.ProductRepository
import fr.thomasbernard03.feed.domain.models.Product
import fr.thomasbernard03.feed.domain.models.Resource
import org.koin.java.KoinJavaComponent.get

class ProductUseCase(
    private val productRepository: ProductRepository = get(ProductRepository::class.java)
) {
    suspend fun getProducts() : Resource<List<Product>> {
        val products = productRepository.getProducts().map { Product(it) }
        return Resource.Success(products)
    }

    suspend fun getProduct(id : Int) : Resource<Product> {
        val product = productRepository.getProduct(id)
        return Resource.Success(Product(product))
    }
}