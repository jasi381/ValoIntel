package com.jasmeet.valorantapi.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jasmeet.valorantapi.presentation.screens.HomeScreen
import com.jasmeet.valorantapi.presentation.screens.Screens
import com.jasmeet.valorantapi.presentation.screens.SplashScreen
import com.jasmeet.valorantapi.presentation.screens.agentUuid
import com.jasmeet.valorantapi.presentation.screens.agents.AgentDetailScreen
import com.jasmeet.valorantapi.presentation.screens.agents.AgentsScreen
import com.jasmeet.valorantapi.presentation.screens.buddies.BuddiesScreen
import com.jasmeet.valorantapi.presentation.screens.bundles.BundlesScreen
import com.jasmeet.valorantapi.presentation.screens.currency.CurrenciesScreen
import com.jasmeet.valorantapi.presentation.screens.mapUUid
import com.jasmeet.valorantapi.presentation.screens.maps.MapDetailsScreen
import com.jasmeet.valorantapi.presentation.screens.maps.MapsScreen
import com.jasmeet.valorantapi.presentation.screens.playerCards.PlayerCardsScreen
import com.jasmeet.valorantapi.presentation.screens.sprays.SpraysScreen
import com.jasmeet.valorantapi.presentation.screens.weaponUUid
import com.jasmeet.valorantapi.presentation.screens.weapons.WeaponDetailsScreen
import com.jasmeet.valorantapi.presentation.screens.weapons.WeaponsScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ValoIntelNavigation(
    navHostController: NavHostController,
) {


    NavHost(
        navController = navHostController,
        startDestination = Screens.SplashScreen.route,
        modifier = Modifier.background(Color(0xff101118))
    ) {


        /**
         * Splash Screen
         */
        composable(
            route = Screens.SplashScreen.route,
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ) {
            SplashScreen(navHostController = navHostController)

        }

        /**
         * Home Screen
         */
        composable(
            route = Screens.HomeScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ) {
            HomeScreen(navHostController = navHostController)

        }

        /**
         * Agents Screen
         */
        composable(
            route = Screens.AgentsScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ) {
            AgentsScreen(navHostController = navHostController)

        }

        composable(
            route = Screens.AgentDetailScreen.route,

            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() },
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

        /**
         * Weapons Screen
         */

        composable(
            route = Screens.WeaponsScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ) {
            WeaponsScreen(navHostController = navHostController)

        }

        composable(
            route = Screens.WeaponDetailsScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() },
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
                weaponUUID = it.arguments?.getString(weaponUUid)
            )
        }

        /**
         * Maps Screen
         */
        composable(
            route = Screens.MapsScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ) {
            MapsScreen(navHostController = navHostController)

        }

        composable(
            route = Screens.MapDetailsScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() },
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

        /**
         * Currencies Screen
         */

        composable(
            route = Screens.CurrenciesScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ) {
            CurrenciesScreen(navHostController = navHostController)

        }

        /**
         * Buddies Screen
         */
        composable(
            route = Screens.BuddiesScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ){
            BuddiesScreen(navHostController = navHostController)
        }

        /**
         * Bundles Screen
         */

        composable(
            route = Screens.BundlesScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ){
            BundlesScreen(navHostController = navHostController)
        }


        /**
         * Player Cards Screen
         */

        composable(
            route = Screens.PlayerCardsScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }

        ){
            PlayerCardsScreen(navHostController = navHostController)
        }

        /**
         * Sprays Screen
         */
        composable(
            route = Screens.SpraysScreen.route,
            enterTransition = { customEnterTransition() },
            exitTransition = { customExitTransition() },
            popExitTransition = { customExitTransition() },
            popEnterTransition = { customEnterTransition() }
        ){
            SpraysScreen(navHostController = navHostController)
        }

    }



}

private fun customExitTransition() = slideOutHorizontally(
    targetOffsetX = {
        1000
    },
    animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
)


private fun customEnterTransition() = slideInHorizontally(
    initialOffsetX = {
        1000
    },
    animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
)

