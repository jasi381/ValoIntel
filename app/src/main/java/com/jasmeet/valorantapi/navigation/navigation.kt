package com.jasmeet.valorantapi.navigation

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
import com.jasmeet.valorantapi.screens.SplashScreen
import com.jasmeet.valorantapi.screens.agentUuid
import com.jasmeet.valorantapi.screens.weaponUUid
import com.jasmeet.valorantapi.screens.WeaponDetailsScreen
import com.jasmeet.valorantapi.screens.WeaponsScreen

@Composable
fun ValoIntelNavigation(
    navHostController: NavHostController,
) {


    NavHost(
        navController = navHostController,
        startDestination = Screens.SplashScreen.route,
    ) {

        composable(
            route = Screens.SplashScreen.route
        ) {
            SplashScreen(navHostController = navHostController)

        }
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
                navArgument(agentUuid){
                    type = NavType.StringType
                    defaultValue = "Default"
                    nullable = true
                }
            )
        ) {
            AgentDetailScreen(
                navHostController = navHostController,
                agentUUID = it.arguments?.getString(agentUuid)
            )


        }
        composable(
            route = Screens.WeaponsScreen.route
        ) {
            WeaponsScreen(navHostController = navHostController)

        }

        composable(
            route = Screens.WeaponDetailsScreen.route,
            arguments = listOf(
                navArgument(weaponUUid){
                    type = NavType.StringType
                    defaultValue = "Default"
                    nullable = true
                }
            )
        ){
            WeaponDetailsScreen(
                navHostController = navHostController,
                weaponUUID = it.arguments?.getString(weaponUUid))
        }
    }



}