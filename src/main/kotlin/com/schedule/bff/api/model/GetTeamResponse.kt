package com.schedule.bff.api.model

import com.schedule.bff.model.User
import java.time.LocalDate

data class GetTeamResponse(
    val id: Long,
    val name: String,
    val creationDate: LocalDate,
    val adminId: Long,
    val members: List<User>,
    val color: String
)