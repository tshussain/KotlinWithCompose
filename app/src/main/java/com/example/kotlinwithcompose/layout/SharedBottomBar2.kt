package com.example.kotlinwithcompose.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kotlinwithcompose.screens.LocalNavController
import com.example.kotlinwithcompose.screens.Routes

data class NavBarIcon(
    val route: String,
    val icon: ImageVector
)
val items = listOf(NavBarIcon(route=Routes.Main.route, icon= Icons.Filled.Home),
    NavBarIcon(route=Routes.About.go("Franz"), icon=Icons.Filled.Info),
    NavBarIcon(route=Routes.Contact.go("Julie","Paris"), icon=Icons.Filled.Phone))
@Composable
fun SharedBottomBar2() {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.route) },
                selected = currentDestination?.hierarchy?.any {
                    currentDestination.route?.substringBefore('/') ==
                    item.route.substringBefore('/') } == true,
                onClick = { navController.navigate(item.route)}
            )
        }
    }
}

