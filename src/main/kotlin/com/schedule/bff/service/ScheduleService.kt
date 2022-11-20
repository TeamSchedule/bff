package com.schedule.bff.service

import com.schedule.bff.api.model.GetTeamByIdResponse
import com.schedule.bff.api.model.GetTeamInviteCriteria
import com.schedule.bff.api.model.TeamInviteStatus
import com.schedule.bff.client.model.Team
import com.schedule.bff.client.model.TeamInvite
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

interface IScheduleService {
    fun getTeamById(id: Long, token: String): Team
    fun getTeamInvite(
        token: String,
        getTeamInviteCriteria: GetTeamInviteCriteria,
        status: TeamInviteStatus,
        teamId: Optional<Long>
    ): TeamInvite
}

@Service
class ScheduleService(
    @Qualifier("scheduleRestTemplate") private val scheduleRestTemplate: RestTemplate
) : IScheduleService {
    override fun getTeamById(id: Long, token: String): Team {
        val httpHeaders = HttpHeaders()
        httpHeaders.set("Authorization", token)
        return scheduleRestTemplate
            .exchange(
                "/team/{id}",
                HttpMethod.GET,
                HttpEntity<Unit>(httpHeaders),
                GetTeamByIdResponse::class.java,
                id
            )
            .body!!
            .team
    }

    override fun getTeamInvite(
        token: String,
        getTeamInviteCriteria: GetTeamInviteCriteria,
        status: TeamInviteStatus,
        teamId: Optional<Long>
    ): TeamInvite {
        val httpHeaders = HttpHeaders()
        httpHeaders.set("Authorization", token)
        return scheduleRestTemplate
            .exchange(
                "/team/invite?criteria={criteria}&status={status}&teamId={teamId}",
                HttpMethod.GET,
                HttpEntity<Unit>(httpHeaders),
                TeamInvite::class.java,
                mapOf("criteria" to getTeamInviteCriteria, "status" to status, "teamId" to teamId.or(null).get())
            )
            .body!!
    }

}