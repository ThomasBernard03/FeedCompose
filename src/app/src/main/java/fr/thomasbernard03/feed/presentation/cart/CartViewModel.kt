package fr.thomasbernard03.feed.presentation.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.thomasbernard03.feed.domain.models.Resource
import fr.thomasbernard03.feed.domain.usecases.CartUseCase
import fr.thomasbernard03.feed.domain.wrappers.ProductWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class CartViewModel(
    private val cartUseCase: CartUseCase = get(CartUseCase::class.java)
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    fun onEvent(event: CartEvent) {
        when (event) {
            CartEvent.OnGetCart -> onGetCart()
            is CartEvent.OnAddProduct -> onAddProduct(event.product)
            is CartEvent.OnRemoveProduct -> onRemoveProduct(event.product)
        }
    }

    private fun onGetCart() {
        viewModelScope.launch {
            when(val products = cartUseCase.getCartProducts()){
                is Resource.Success -> {
                    _uiState.update { it.copy(products = products.data)}
                }
                is Resource.Error -> {
                    Log.e("CartViewModel", "Error while getting cart products")
                }
            }
        }
    }

    private fun onAddProduct(product: ProductWrapper) {
        viewModelScope.launch {
            when(cartUseCase.addProduct(product)){
                is Resource.Success -> {
                    val updatedProducts = _uiState.value.products?.map {
                        if (it.id == product.id)
                            it.copy(quantity = it.quantity + 1)
                        else it
                    }
                    _uiState.update { it.copy(products = updatedProducts)}
                }
                is Resource.Error -> {
                    Log.e("CartViewModel", "Error while adding product")
                }
            }
        }
    }

    private fun onRemoveProduct(product: ProductWrapper) {
        viewModelScope.launch {
            when(cartUseCase.removeProduct(product)){
                is Resource.Success -> {
                    val updatedProducts = _uiState.value.products?.map {
                        if (it.id == product.id)
                            it.copy(quantity = it.quantity - 1)
                        else it
                    }?.filter { it.quantity > 0 }
                    _uiState.update { it.copy(products = updatedProducts)}
                }
                is Resource.Error -> {
                    Log.e("CartViewModel", "Error while removing product")
                }
            }
        }
    }
}