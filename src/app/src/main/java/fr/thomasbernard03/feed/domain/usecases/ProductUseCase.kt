package fr.thomasbernard03.feed.domain.usecases

import fr.thomasbernard03.feed.data.repositories.CartRepository
import fr.thomasbernard03.feed.data.repositories.ProductRepository
import fr.thomasbernard03.feed.domain.models.Product
import fr.thomasbernard03.feed.domain.models.Resource
import fr.thomasbernard03.feed.domain.wrappers.ProductWrapper
import org.koin.java.KoinJavaComponent.get

class ProductUseCase(
    private val productRepository: ProductRepository = get(ProductRepository::class.java),
    private val cartRepository: CartRepository = get(CartRepository::class.java)
)  {
    suspend fun getProducts() : Resource<List<Product>> {
        val products = productRepository.getProducts().map { Product(it) }
        return Resource.Success(products)
    }

    suspend fun getProductWrapper(id : Int) : Resource<ProductWrapper> {
        val product = productRepository.getProduct(id)
        val quantity = cartRepository.getQuantityOfProduct(id)

        val wrapper = ProductWrapper(
            id = product.id,
            label = product.label,
            description = product.description,
            image = product.image,
            price = product.price,
            quantity = quantity
        )

        return Resource.Success(wrapper)
    }

    suspend fun addProductToCart(productId: Int, quantity: Int) {
        cartRepository.addProductToCart(productId, quantity)
    }

    suspend fun updateQuantityOfProduct(productId: Int, quantity: Int) {
        cartRepository.updateQuantityOfProduct(productId, quantity)
    }
}