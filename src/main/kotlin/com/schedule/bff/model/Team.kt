package com.schedule.bff.model

import java.time.LocalDate

data class Team(
    val id: Long,
    val name: String,
    val creationDate: LocalDate,
    val adminId: Long,
    val membersIds: List<Long>,
    val color: String
)