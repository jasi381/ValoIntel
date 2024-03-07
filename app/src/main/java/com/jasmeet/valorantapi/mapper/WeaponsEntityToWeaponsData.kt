package com.jasmeet.valorantapi.mapper


import com.jasmeet.valorantapi.data.weaponsApiResponse.AdsStats
import com.jasmeet.valorantapi.data.weaponsApiResponse.AirBurstStats
import com.jasmeet.valorantapi.data.weaponsApiResponse.AltShotgunStats
import com.jasmeet.valorantapi.data.weaponsApiResponse.Data
import com.jasmeet.valorantapi.data.weaponsApiResponse.GridPosition
import com.jasmeet.valorantapi.data.weaponsApiResponse.ShopData
import com.jasmeet.valorantapi.data.weaponsApiResponse.WeaponStats
import com.jasmeet.valorantapi.room.weapons.WeaponsEntity

fun WeaponsEntity.toWeaponsData(): Data {
    return Data(
        assetPath = "",
        category = category,
        defaultSkinUuid = "",
        displayIcon = displayIcon,
        displayName = displayName,
        killStreamIcon = "",
        shopData = ShopData(
            assetPath = "",
            canBeTrashed = false,
            category = "",
            categoryText = "",
            cost = 1,
            gridPosition = GridPosition(
                column = 1,
                row = 1

            ),
            image = "",
            newImage = "",
            newImage2 = "",
            shopOrderPriority = 1

        ),
        skins= skins,
        uuid= "",
        weaponStats= WeaponStats(
            adsStats = AdsStats(
                burstCount = 0,
                fireRate = 0.0,
                firstBulletAccuracy = 0.0,
                runSpeedMultiplier = 0.0,
                zoomMultiplier = 0.0
            ),
            airBurstStats = AirBurstStats(
                burstDistance = 0.0,
                shotgunPelletCount = 0
            ),
            altFireType = "",
            altShotgunStats = AltShotgunStats(
                burstRate = 0.0,
                shotgunPelletCount = 0
            ),
            damageRanges = emptyList(),
            equipTimeSeconds = 0.0,
            feature = "",
            fireMode = "",
            fireRate = 0.0,
            firstBulletAccuracy = 0.0,
            magazineSize = 0,
            reloadTimeSeconds = 0.0,
            runSpeedMultiplier = 0.0,
            shotgunPelletCount = 0,
            wallPenetration = ""

        )

    )
}