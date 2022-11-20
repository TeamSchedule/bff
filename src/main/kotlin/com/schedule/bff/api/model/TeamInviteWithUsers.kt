package com.schedule.bff.api.model

import com.schedule.bff.client.model.TeamShortDescription

data class TeamInviteWithUsers(
    val id: Long,
    val inviting: UserWithAvatar,
    val invited: UserWithAvatar,
    val date: String,
    val status: TeamInviteStatus,
    val team: TeamShortDescription
)