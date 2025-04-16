package com.example.volunteerassist

import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun BottomNavigationBar() {
    // Define the list of navigation routes using the data class
    val navRoutes = listOf(
        //TODO: Add more routing navigation page here
        NavRoute("home", Icons.Filled.Home, "Home"),
        NavRoute("report", Icons.Filled.Info, "Report"),
    )

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.padding(bottom = 20.dp),
                backgroundColor = Color(0xFFADD8E6)
            ) //light blue color
            {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navRoutes.forEach { navRoute ->
                    BottomNavigationItem(
                        icon = { Icon(navRoute.icon, contentDescription =
                        navRoute.label) },
                        label = { Text(navRoute.label) },
                        selected = currentDestination?.route == navRoute.route,
                        onClick = {
                            navController.navigate(navRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            //TODO: ADD relevant navigation routing here
            composable("home") { Home() }
            composable("report") { VolunteerReportScreen() }
        }
    }
}