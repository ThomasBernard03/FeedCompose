package fr.thomasbernard03.feed.domain.usecases

import fr.thomasbernard03.feed.data.repositories.CartRepository
import fr.thomasbernard03.feed.domain.models.Resource
import fr.thomasbernard03.feed.domain.wrappers.ProductWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class CartUseCase(
    private val cartRepository: CartRepository = get(CartRepository::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java)
) {

    suspend fun getCartProducts() : Resource<List<ProductWrapper>>{
        return withContext(ioDispatcher){
            val products = cartRepository.getCartProductsWithQuantity().map {
                ProductWrapper(
                    id = it.productEntity.id,
                    label = it.productEntity.label,
                    description = it.productEntity.description,
                    image = it.productEntity.image,
                    price = it.productEntity.price,
                    quantity = it.quantity
                )
            }

            return@withContext Resource.Success(products)
        }
    }

    suspend fun addProduct(product: ProductWrapper) : Resource<Unit>{
        return withContext(ioDispatcher){
            try {
                cartRepository.addProductToCart(product.id)
                return@withContext Resource.Success(Unit)
            }
            catch (e: Exception){
                e.printStackTrace()
                return@withContext Resource.Error(e.message ?: "Error while adding product")
            }

        }
    }

    suspend fun removeProduct(product: ProductWrapper) : Resource<Unit>{
        return withContext(ioDispatcher){
            try {
                cartRepository.removeProductToCart(product.id)
                return@withContext Resource.Success(Unit)
            }
            catch (e: Exception){
                e.printStackTrace()
                return@withContext Resource.Error(e.message ?: "Error while removing product")
            }
        }
    }
}