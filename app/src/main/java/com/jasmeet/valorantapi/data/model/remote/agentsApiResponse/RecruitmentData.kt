package com.jasmeet.valorantapi.data.model.remote.agentsApiResponse

data class RecruitmentData(
    val counterId: String,
    val endDate: String,
    val levelVpCostOverride: Int,
    val milestoneId: String,
    val milestoneThreshold: Int,
    val startDate: String,
    val useLevelVpCostOverride: Boolean
)