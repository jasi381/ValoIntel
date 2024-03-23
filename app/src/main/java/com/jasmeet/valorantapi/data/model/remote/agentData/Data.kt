package com.jasmeet.valorantapi.data.model.remote.agentData

data class Data(
    val abilities: List<com.jasmeet.valorantapi.data.model.remote.agentData.Ability>,
    val assetPath: String,
    val background: String,
    val backgroundGradientColors: List<String>,
    val bustPortrait: String,
    val characterTags: Any,
    val description: String,
    val developerName: String,
    val displayIcon: String,
    val displayIconSmall: String,
    val displayName: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val killfeedPortrait: String,
    val recruitmentData: Any,
    val role: com.jasmeet.valorantapi.data.model.remote.agentData.Role,
    val uuid: String,
    val voiceLine: String
)