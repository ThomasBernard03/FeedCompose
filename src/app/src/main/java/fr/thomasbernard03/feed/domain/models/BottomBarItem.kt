package fr.thomasbernard03.feed.domain.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    @StringRes val label : Int,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
    val route : String,
)