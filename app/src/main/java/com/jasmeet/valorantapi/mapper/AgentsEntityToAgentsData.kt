package com.jasmeet.valorantapi.mapper

import com.jasmeet.valorantapi.data.Data
import com.jasmeet.valorantapi.data.RecruitmentData
import com.jasmeet.valorantapi.data.Role
import com.jasmeet.valorantapi.room.data.AgentsEntity

fun AgentsEntity.toAgentsData(): Data {
    return Data(
        uuid = uuid,
        abilities = abilities,
        backgroundGradientColors = backgroundGradientColors,
        background = background,
        characterTags = emptyList(),
        description = description,
        displayName = displayName,
        fullPortrait = fullPortrait,
        fullPortraitV2 = fullPortraitV2,
        bustPortrait = bustPortrait,
        developerName = developerName,
        assetPath = "",
        displayIcon = "",
        displayIconSmall = "",
        isBaseContent = false,
        isPlayableCharacter = false,
        isFullPortraitRightFacing = false,
        isAvailableForTest = false,
        killfeedPortrait = "",
        recruitmentData = RecruitmentData(
            counterId = "" ,
            endDate = "",
            levelVpCostOverride = 0,
            milestoneId =" ",
            milestoneThreshold = 0,
            startDate = " ",
            useLevelVpCostOverride = false
        ),
        role = Role(
            assetPath = "",
            description = "",
            displayIcon =  "",
            displayName = "",
            uuid = ""
        ),
        voiceLine = ""

    )
}
