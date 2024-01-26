package fr.thomasbernard03.feed.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.thomasbernard03.feed.R
import fr.thomasbernard03.feed.commons.navigation.Navigator
import fr.thomasbernard03.feed.domain.models.BottomBarItem
import fr.thomasbernard03.feed.presentation.cart.CartScreen
import fr.thomasbernard03.feed.presentation.cart.CartViewModel
import fr.thomasbernard03.feed.presentation.components.HomeBottomBar
import fr.thomasbernard03.feed.presentation.home.HomeScreen
import fr.thomasbernard03.feed.presentation.home.HomeViewModel
import fr.thomasbernard03.feed.presentation.product.ProductScreen
import fr.thomasbernard03.feed.presentation.product.ProductViewModel
import fr.thomasbernard03.feed.presentation.theme.FeedTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

class MainActivity(
    private val navigator : Navigator = get(Navigator::class.java)
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
//        enableEdgeToEdge(
//            navigationBarStyle = SystemBarStyle.dark(
//                scrim = Color.BLACK
//            )
//        )
        
        setContent {
            FeedTheme {
                val navController = rememberNavController()
                val bottomBarItems = remember {
                    listOf(
                        BottomBarItem(
                            label = R.string.home,
                            unselectedIcon = Icons.Outlined.Home,
                            selectedIcon = Icons.Filled.Home,
                            route = "home"
                        ),
                        BottomBarItem(
                            label = R.string.news,
                            unselectedIcon = Icons.Outlined.DateRange,
                            selectedIcon = Icons.Filled.DateRange,
                            route = "news"
                        ),
                        BottomBarItem(
                            label = R.string.basket,
                            unselectedIcon = Icons.Outlined.ShoppingCart,
                            selectedIcon = Icons.Filled.ShoppingCart,
                            route = "cart"
                        )
                    )
                }

                LaunchedEffect(Unit){
                    navigator.sharedFlow.onEach {
                        when(it){
                            is Navigator.NavigationEvent.NavigateTo -> {
                                navController.navigate(it.destination.route) {
                                    // avoiding multiple copies on the top of the back stack
                                    launchSingleTop = true

                                    if(!it.popupTo.isNullOrEmpty())
                                        popUpTo(it.popupTo)
                                }
                            }
                            is Navigator.NavigationEvent.GoBack -> {
                                navController.navigateUp()
                            }
                        }
                    }.launchIn(this)
                }

                Scaffold(
                    bottomBar = {
                        HomeBottomBar(
                            destinations = bottomBarItems,
                            currentDestination = navController.currentBackStackEntryAsState().value?.destination,
                            onNavigateToDestination = {
                                navController.navigate(it) {
                                    popUpTo(navController.graph.id)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ){
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ){
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                val homeViewModel : HomeViewModel = viewModel()
                                val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
                                HomeScreen(
                                    state = uiState,
                                    onEvent = homeViewModel::onEvent
                                )
                            }
                            composable("news") {
                                Text(text = "News")
                            }
                            composable(
                                route = "cart"
                            ) {
                                val cartViewModel : CartViewModel = viewModel()
                                val uiState by cartViewModel.uiState.collectAsStateWithLifecycle()
                                CartScreen(state = uiState, onEvent = cartViewModel::onEvent)
                            }
                            composable(
                                route = "product/{id}",
                                arguments = listOf(
                                    navArgument("id") {
                                        type = NavType.IntType
                                    }
                                )
                            ) {
                                val id = it.arguments?.getInt("id")!!
                                val productViewModel : ProductViewModel = viewModel()
                                val uiState by productViewModel.uiState.collectAsStateWithLifecycle()
                                ProductScreen(id, uiState, productViewModel::onEvent)
                            }
                        }
                    }
                }
            }
        }
    }
}