package com.jasmeet.valorantapi.data.mapper

//import com.jasmeet.valorantapi.remote.mapsData.Data
import com.jasmeet.valorantapi.data.model.local.MapsEntity
import com.jasmeet.valorantapi.data.model.remote.mapsData.Data

fun MapsEntity.toMapsData(): Data {
    return Data(
        uuid =  uuid,
        displayName = displayName,
        listViewIcon = listViewIcon,
        assetPath = "",
        callouts = emptyList(),
        coordinates = "",
        displayIcon = "",
        listViewIconTall = "",
        mapUrl = "",
        narrativeDescription = "",
        premierBackgroundImage = "",
        splash = "",
        stylizedBackgroundImage = "",
        tacticalDescription = "",
        xMultiplier = 0.0,
        xScalarToAdd = 0.0,
        yMultiplier = 0.0,
        yScalarToAdd = 0.0
    )

}