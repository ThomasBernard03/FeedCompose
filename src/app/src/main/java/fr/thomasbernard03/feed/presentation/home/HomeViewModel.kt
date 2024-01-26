package fr.thomasbernard03.feed.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.thomasbernard03.feed.commons.navigation.Navigator
import fr.thomasbernard03.feed.domain.models.Product
import fr.thomasbernard03.feed.domain.models.Resource
import fr.thomasbernard03.feed.domain.usecases.ProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class HomeViewModel(
    private val productUseCase: ProductUseCase = get(ProductUseCase::class.java),
    private val navigator: Navigator = get(Navigator::class.java)
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnGetProducts -> onGetProducts()
            is HomeEvent.OnProductClicked -> onProductClicked(event.product)
            is HomeEvent.OnQueryChanged -> onQueryChanged(event.query)
        }
    }

    private fun onGetProducts(){
        viewModelScope.launch {
            _uiState.update { it.copy(products = null) }
            when(val products = productUseCase.getProducts()){
                is Resource.Success -> {
                    _uiState.update { it.copy(products = products.data) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(products = emptyList()) }
                }
            }
        }
    }

    private fun onProductClicked(product : Product){
        navigator.navigateTo(Navigator.MainDestination.ProductDetail(product.id))
    }

    private fun onQueryChanged(query : String) =
        _uiState.update { it.copy(query = query) }
}