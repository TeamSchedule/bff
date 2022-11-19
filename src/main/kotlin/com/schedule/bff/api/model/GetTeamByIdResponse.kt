package com.schedule.bff.api.model

import com.schedule.bff.client.model.Team

data class GetTeamByIdResponse(
    val team: Team
)