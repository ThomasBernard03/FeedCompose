package fr.thomasbernard03.feed.data.repositories.implementations

import fr.thomasbernard03.feed.data.local.dao.ProductDao
import fr.thomasbernard03.feed.data.local.entities.ProductEntity
import fr.thomasbernard03.feed.data.remote.dto.ProductDto
import fr.thomasbernard03.feed.data.remote.services.ApiService
import fr.thomasbernard03.feed.data.repositories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class ProductRepositoryImpl(
    private val productDao: ProductDao = get(ProductDao::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java),
    private val apiService: ApiService = get(ApiService::class.java),
) : ProductRepository {

    override suspend fun getProducts() : List<ProductEntity> {
        return withContext(ioDispatcher) {
            val products = productDao.getAll()

            // If products is not empty, we return them
            if (products.isNotEmpty())
                return@withContext products

            // Else get products from API
            val productsApi = apiService.getProducts().map { ProductEntity(it) }
            productDao.insertOrUpdate(productsApi)

            return@withContext productsApi
        }
    }

    override suspend fun getProduct(id : Int) : ProductEntity {
        return withContext(ioDispatcher) {
            return@withContext productDao.getProduct(id)
        }
    }
}