package com.jasmeet.valorantapi.data.weaponData

data class WeaponStats(
    val adsStats: AdsStats,
    val airBurstStats: Any,
    val altFireType: String,
    val altShotgunStats: Any,
    val damageRanges: List<DamageRange>,
    val equipTimeSeconds: Double,
    val feature: String,
    val fireMode: Any,
    val fireRate: Double,
    val firstBulletAccuracy: Double,
    val magazineSize: Int,
    val reloadTimeSeconds: Double,
    val runSpeedMultiplier: Double,
    val shotgunPelletCount: Int,
    val wallPenetration: String
)