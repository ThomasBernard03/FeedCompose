package fr.thomasbernard03.feed.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import fr.thomasbernard03.feed.domain.models.BottomBarItem


@Composable
fun HomeBottomBar(
    destinations: List<BottomBarItem>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (route: String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .height(70.dp),
    ) {
        destinations.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.background,
                    selectedTextColor = MaterialTheme.colorScheme.background,
                    indicatorColor = MaterialTheme.colorScheme.background,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                ),
                selected = selected,
                onClick = { onNavigateToDestination(destination.route) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        imageVector = icon,
                        modifier = Modifier.size(16.dp),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.label),
                    )
                })
        }
    }
}