package fr.thomasbernard03.feed.domain.usecases

import fr.thomasbernard03.feed.domain.models.Product
import fr.thomasbernard03.feed.domain.models.Resource

class ProductUseCase {

    suspend fun getProducts() : Resource<List<Product>> {
        val sushis = Product(
            id = 1,
            title = "Sushis saumon",
            description = "Saumon, riz vinaigré (2 pièces)",
            picture = "https://static.vecteezy.com/system/resources/previews/011/190/616/original/sushi-with-salmon-png.png",
            price = 2.5,
            rating = 4.7
        )

        val makiThon = Product(
            id = 2,
            title = "Maki thon",
            description = "Thon, riz vinaigré, algue (2 pièces)",
            picture = "https://makesushi31000.fr/wp-content/uploads/2020/05/maki-4.png",
            price = 2.0,
            rating = 4.0
        )

        val chirachiSaumonAvocat = Product(
            id = 3,
            title = "Chirachi saumon avocat",
            description = "Saumon, avocat, riz vinaigré",
            picture = "https://www.sushishop.fr/product-12875-zoom/chirashi-saumon-avocat.png",
            price = 12.0,
            rating = 4.5
        )

        val brochetteBoeufFromage = Product(
            id = 4,
            title = "Brochette boeuf fromage",
            description = "Boeuf, fromage (4 pièces)",
            picture = "https://www.sushiplaza.com/images/Image/Yakitori-Boeuf-Fromage-2.png",
            price = 4.0,
            rating = 5.0
        )

        return Resource.Success(listOf(sushis, makiThon, chirachiSaumonAvocat, brochetteBoeufFromage))
    }
}