package com.schedule.bff.client.model

import com.schedule.bff.api.model.TeamInviteStatus

data class TeamInvite(
    val id: Long,
    val invitingId: Long,
    val invitedId: Long,
    val date: String,
    val inviteStatus: TeamInviteStatus,
    val teamShortDescription: TeamShortDescription
)