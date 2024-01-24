package fr.thomasbernard03.feed.data.repositories.implementations

import fr.thomasbernard03.feed.data.local.dao.ProductDao
import fr.thomasbernard03.feed.data.local.entities.ProductEntity
import fr.thomasbernard03.feed.data.remote.dto.ProductDto
import fr.thomasbernard03.feed.data.repositories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get

class ProductRepositoryImpl(
    private val productDao: ProductDao = get(ProductDao::class.java),
    private val ioDispatcher: CoroutineDispatcher = get(CoroutineDispatcher::class.java)
) : ProductRepository {

    val productsDto = listOf(
        ProductDto(
            id = 1,
            label = "Sushis saumon",
            description = "Saumon, riz vinaigré (2 pièces)",
            image = "https://static.vecteezy.com/system/resources/previews/011/190/616/original/sushi-with-salmon-png.png",
            price = null,
        ),
        ProductDto(
            id = 2,
            label = "Maki thon",
            description = "Thon, riz vinaigré, algue (2 pièces)",
            image = "https://makesushi31000.fr/wp-content/uploads/2020/05/maki-2.png",
            price = 2.0,
        ),
        ProductDto(
            id = 3,
            label = "Maki thon",
            description = "Thon, riz vinaigré, algue (2 pièces)",
            image = "https://makesushi31000.fr/wp-content/uploads/2020/05/maki-3.png",
            price = 2.0,
        ),
        ProductDto(
            id = 4,
            label = "Maki thon",
            description = "Thon, riz vinaigré, algue (2 pièces)",
            image = "https://makesushi31000.fr/wp-content/uploads/2020/05/maki-4.png",
            price = 2.0,
        ),
        ProductDto(
            id = 5,
            label = "Maki avocat",
            description = "Algue, avocat",
            image = "https://makesushi31000.fr/wp-content/uploads/2020/05/maki-2.png",
            price = 4.0,
        ),
        ProductDto(
            id = 12,
            label = "Maki saumon roll",
            description = "Saumon, fromage, riz vinaigré",
            image = "https://makesushi31000.fr/wp-content/uploads/2020/05/maki-12.png",
            price = 12.0,
        ),
        ProductDto(
            id = 13,
            label = "Sashimi saumon",
            description = "Sashimi de saumon (10 pièces)",
            image = "https://makesushi31000.fr/wp-content/uploads/2020/05/sashimi-3-768x768.png",
            price = 14.0,
        ),
    )


    override suspend fun getProducts() : List<ProductEntity> {
        return withContext(ioDispatcher) {
            val products = productDao.getAll()

            // If products is not empty, we return them
            if (products.isNotEmpty())
                return@withContext products

            // Else get products from API
            val productsApi = productsDto.map { ProductEntity(it) }
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