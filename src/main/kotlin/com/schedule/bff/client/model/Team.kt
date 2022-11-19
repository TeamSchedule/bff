package com.schedule.bff.client.model

import java.time.LocalDate

data class Team(
    val id: Long,
    val name: String,
    val creationDate: LocalDate,
    val adminId: Long,
    val membersIds: List<Long>,
    val color: String
)