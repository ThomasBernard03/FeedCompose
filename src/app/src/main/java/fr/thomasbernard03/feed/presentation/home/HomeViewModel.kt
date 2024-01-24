package fr.thomasbernard03.feed.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.thomasbernard03.feed.domain.models.Resource
import fr.thomasbernard03.feed.domain.usecases.MenuUseCase
import fr.thomasbernard03.feed.domain.usecases.ProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class HomeViewModel(
    private val menuUseCase: MenuUseCase = get(MenuUseCase::class.java),
    private val productUseCase: ProductUseCase = get(ProductUseCase::class.java),
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnGetFeaturedMenu -> onGetFeaturedMenus()
            is HomeEvent.OnGetProducts -> onGetProducts()
        }
    }

    private fun onGetFeaturedMenus(){
        viewModelScope.launch {
            _uiState.update { it.copy(featuredMenus = null) }
            when(val menus = menuUseCase.getFeaturedMenus()){
                is Resource.Success -> {
                    _uiState.update { it.copy(featuredMenus = menus.data) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(featuredMenus = emptyList()) }
                }
            }
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
}