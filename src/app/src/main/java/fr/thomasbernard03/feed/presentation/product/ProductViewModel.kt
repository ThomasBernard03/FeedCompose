package fr.thomasbernard03.feed.presentation.product

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

class ProductViewModel(
    private val navigator: Navigator = get(Navigator::class.java),
    private val productUseCase: ProductUseCase = get(ProductUseCase::class.java)
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.OnGetProduct -> onGetProduct(event.id)
            is ProductEvent.OnGoBack -> onGoBack()
            is ProductEvent.OnAddToCart -> onAddToCart(event.product, event.quantity)
            is ProductEvent.OnIncrementQuantity -> onIncrementQuantity()
            is ProductEvent.OnDecrementQuantity -> onDecrementQuantity()
        }
    }

    private fun onGetProduct(id : Int){
        viewModelScope.launch {
            when(val product = productUseCase.getProduct(id)){
                is Resource.Success -> {
                    _uiState.update { it.copy(product = product.data) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(product = null) }
                }
            }
        }
    }

    private fun onAddToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            productUseCase.addProductToCart(product.id, quantity)
            navigator.goBack()
        }
    }

    private fun onGoBack() =
        navigator.goBack()

    private fun onIncrementQuantity() {
        _uiState.update { it.copy(quantity = it.quantity + 1) }
    }

    private fun onDecrementQuantity() {
        if (_uiState.value.quantity == 0) return
        _uiState.update { it.copy(quantity = it.quantity - 1) }
    }
}