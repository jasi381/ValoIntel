package com.jasmeet.valorantapi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jasmeet.valorantapi.screens.AgentDetailScreen
import com.jasmeet.valorantapi.screens.AgentsScreen
import com.jasmeet.valorantapi.screens.HomeScreen
import com.jasmeet.valorantapi.screens.Screens
import com.jasmeet.valorantapi.screens.UUID

@Composable
fun ValoIntelNavigation(
    navHostController: NavHostController,
) {


    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route,
    ) {
        composable(
            route = Screens.HomeScreen.route
        ) {
            HomeScreen(navHostController = navHostController)

        }
        composable(
            route = Screens.AgentsScreen.route
        ) {
            AgentsScreen(navHostController = navHostController)

        }

        composable(
            route = Screens.AgentDetailScreen.route,
            arguments =  listOf(
                navArgument(UUID){
                    type = NavType.StringType
                    defaultValue = "Default"
                    nullable = true
                }
            )
        ) {
            AgentDetailScreen(
                navHostController = navHostController,
                agentUUID = it.arguments?.getString(UUID)
            )


        }
    }



}