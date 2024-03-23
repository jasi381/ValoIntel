package com.jasmeet.valorantapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jasmeet.valorantapi.presentation.screens.AgentDetailScreen
import com.jasmeet.valorantapi.presentation.screens.AgentsScreen
import com.jasmeet.valorantapi.presentation.screens.CurrenciesScreen
import com.jasmeet.valorantapi.presentation.screens.HomeScreen
import com.jasmeet.valorantapi.presentation.screens.MapDetailsScreen
import com.jasmeet.valorantapi.presentation.screens.MapsScreen
import com.jasmeet.valorantapi.presentation.screens.Screens
import com.jasmeet.valorantapi.presentation.screens.SplashScreen
import com.jasmeet.valorantapi.presentation.screens.agentUuid
import com.jasmeet.valorantapi.presentation.screens.weaponUUid
import com.jasmeet.valorantapi.presentation.screens.WeaponDetailsScreen
import com.jasmeet.valorantapi.presentation.screens.WeaponsScreen
import com.jasmeet.valorantapi.presentation.screens.mapUUid

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

        composable(
            route = Screens.MapsScreen.route
        ) {
            MapsScreen(navHostController = navHostController)

        }

        composable(
            route = Screens.MapDetailsScreen.route,
            arguments = listOf(
                navArgument(mapUUid){
                    type = NavType.StringType
                    defaultValue = "Default"
                    nullable = true
                }
            )
        ){
            MapDetailsScreen(
                navHostController = navHostController,
                mapUUID = it.arguments?.getString(mapUUid))
        }


        composable(
            route = Screens.CurrenciesScreen.route
        ) {
            CurrenciesScreen(navHostController = navHostController)

        }
    }



}