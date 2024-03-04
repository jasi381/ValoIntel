package com.jasmeet.valorantapi.screens


const val UUID = "uuid"

sealed class Screens(val route:String){
    data object HomeScreen:Screens("home")
    data object AgentsScreen:Screens("agents")
    data object AgentDetailScreen:Screens(
        "agentDetail/{$UUID}"
    ){
        fun passUuid(uuid:String):String{
            return this.route.replace("{$UUID}",uuid)
        }
    }
}