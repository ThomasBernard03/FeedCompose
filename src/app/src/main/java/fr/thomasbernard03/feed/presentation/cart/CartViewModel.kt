package fr.thomasbernard03.feed.presentation.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.thomasbernard03.feed.domain.models.Resource
import fr.thomasbernard03.feed.domain.usecases.CartUseCase
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
}