package com.schedule.bff.api.model

data class GetTeamInvitesResponse(
    val invites: List<TeamInviteWithUsers>
)