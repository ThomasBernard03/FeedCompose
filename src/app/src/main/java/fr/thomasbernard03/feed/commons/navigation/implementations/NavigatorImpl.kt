package fr.thomasbernard03.feed.commons.navigation.implementations

import fr.thomasbernard03.feed.commons.navigation.Navigator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigatorImpl : Navigator {
    private val _sharedFlow = MutableSharedFlow<Navigator.NavigationEvent>(extraBufferCapacity = 1)
    override val sharedFlow = _sharedFlow.asSharedFlow()

    override fun navigateTo(route: Navigator.Destination, popupTo : String?) {
        _sharedFlow.tryEmit(Navigator.NavigationEvent.NavigateTo(route, popupTo))
    }

    override fun goBack() {
        _sharedFlow.tryEmit(Navigator.NavigationEvent.GoBack)
    }
}