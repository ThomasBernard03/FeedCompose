package fr.thomasbernard03.feed.commons.navigation

import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val sharedFlow : SharedFlow<NavigationEvent>

    fun navigateTo(route: Destination, popupTo : String? = null)

    fun goBack()

    sealed class NavigationEvent {
        data class NavigateTo(val destination: Destination, val popupTo : String? = null) : NavigationEvent()
        data object GoBack : NavigationEvent()
    }

    abstract class Destination {
        abstract val route: String
    }

    sealed class MainDestination(override val route: String) : Destination() {
        data object Home : MainDestination("home")
        data object News : MainDestination("stocks")
        data object Basket : MainDestination("basket")
        data class ProductDetail(val id: Int) : MainDestination("product/$id")
    }
}