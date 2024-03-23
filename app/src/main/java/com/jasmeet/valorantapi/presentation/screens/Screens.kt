package com.jasmeet.valorantapi.presentation.screens


const val agentUuid = "uuid"
const val weaponUUid = "uuid"
const val mapUUid = "uuid"


sealed class Screens(val route:String){
    data object SplashScreen: Screens("splash")
    data object HomeScreen: Screens("home")
    data object AgentsScreen: Screens("agents")

    data object WeaponsScreen: Screens("weapons")
    data object MapsScreen: Screens("maps")
    data object AgentDetailScreen: Screens(
        "agentDetail/{$agentUuid}"
    ){
        fun passUuid(uuid:String):String{
            return this.route.replace("{$agentUuid}",uuid)
        }
    }

    data object WeaponDetailsScreen: Screens(
        "weaponDetails/{$weaponUUid}"
    ){
        fun passUuid(uuid:String):String{
            return this.route.replace("{$weaponUUid}",uuid)
        }
    }

    data object MapDetailsScreen: Screens(
        "mapDetails/{$mapUUid}"
    ){
        fun passUuid(uuid:String):String{
            return this.route.replace("{$mapUUid}",uuid)
        }
    }
}