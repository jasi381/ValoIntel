package com.jasmeet.valorantapi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jasmeet.valorantapi.appComponents.HomeItem
import com.jasmeet.valorantapi.appComponents.TopAppBarComponent


@Composable
fun HomeScreen(navHostController: NavHostController) {

    Scaffold(topBar = {
        TopAppBarComponent()
    }) { innerPadding ->
        Column(
            Modifier
                .padding(top = 5.dp)
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding(), top = innerPadding.calculateTopPadding())
                .verticalScroll(rememberScrollState())
        )
        {
            HomeItem(
                imageUrl = "https://cdn.thespike.gg/JLMIE%2FAgent%20Roles_1691015512633.jpg",
                title = "Agents",
                onItemClick = {
                    navHostController.navigate(Screens.AgentsScreen.route)
                },
                modifier = Modifier.padding(vertical = 12.dp).height(160.dp)
            )
            HomeItem(
                imageUrl = "https://media.valorant-api.com/playercards/adbd4077-4f0f-57c7-d9cd-7e9bc4244646/wideart.png",
                title = "Arsenal",
                onItemClick = {
                              navHostController.navigate(Screens.WeaponsScreen.route)
                },
                modifier = Modifier.height(80.dp)
            )
            HomeItem(
                imageUrl = "https://media.valorant-api.com/maps/d960549e-485c-e861-8d71-aa9d1aed12a2/splash.png",
                title = "Maps",
                onItemClick = {},
                modifier = Modifier.padding(vertical = 12.dp).height(80.dp)
            )
            HomeItem(
                imageUrl = "https://server.blix.gg/imgproxy/4gie3aDsfae3MWhkS42Sv5IZTux5SMIR3tYP36QpYf4/rs:fit:460:200:0/g:no/aHR0cDovL21pbmlvOjkwMDAvaW1hZ2VzLzg5YzQ2ZWQ0YmYxYjQwZjc5NDhlOTMyYWJjNDhkODkwLnBuZw.webp",
                title = "Currencies",
                onItemClick = {},
                modifier = Modifier.height(80.dp)
            )
            HomeItem(
                imageUrl = "https://staticg.sportskeeda.com/editor/2021/09/5916c-16304972176281.png",
                title = "Buddies",
                onItemClick = {},
                modifier = Modifier.padding(vertical = 12.dp).height(80.dp)
            )
            HomeItem(
                imageUrl = "https://media.valorant-api.com/playercards/fc209787-414b-10d0-dcac-04832fc2c654/wideart.png",
                title = "Player Cards",
                onItemClick = {},
                modifier = Modifier.height(80.dp)
            )
        }
    }
}


