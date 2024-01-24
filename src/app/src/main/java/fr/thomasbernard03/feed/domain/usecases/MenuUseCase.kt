package fr.thomasbernard03.feed.domain.usecases

import fr.thomasbernard03.feed.domain.models.Menu
import fr.thomasbernard03.feed.domain.models.Resource

class MenuUseCase {

    suspend fun getFeaturedMenus() : Resource<List<Menu>> {
        val menu1 = Menu(
            id = 1,
            title = "Menu burger",
            description = "Description du menu 1",
            picture = "https://png.pngtree.com/png-clipart/20230928/original/pngtree-burger-png-images-png-image_13164941.png",
            price = 12.0
        )
        val menu2 = Menu(
            id = 2,
            title = "Menu hot dog",
            description = "Description du menu 2",
            picture = "https://i.pinimg.com/originals/9e/ca/5b/9eca5b7cdf5822aaf6cdb86bb7b53437.png",
            price = 8.0
        )

        val menu3 = Menu(
            id = 1,
            title = "Menu burger XL",
            description = "Description du menu 1",
            picture = "https://png.pngtree.com/png-clipart/20230928/original/pngtree-burger-png-images-png-image_13164941.png",
            price = 14.0
        )
        val menu4 = Menu(
            id = 2,
            title = "Menu hot dog XL",
            description = "Description du menu 2",
            picture = "https://i.pinimg.com/originals/9e/ca/5b/9eca5b7cdf5822aaf6cdb86bb7b53437.png",
            price = 10.0
        )

        return Resource.Success(listOf(menu1, menu2, menu3, menu4))
    }
}