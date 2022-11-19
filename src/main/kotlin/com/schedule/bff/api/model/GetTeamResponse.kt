package com.schedule.bff.api.model

import java.time.LocalDate

data class GetTeamResponse(
    val id: Long,
    val name: String,
    val creationDate: LocalDate,
    val adminId: Long,
    val members: List<UserWithAvatar>,
    val color: String,
    val avatar: String
)