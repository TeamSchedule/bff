package com.schedule.bff.service

import com.schedule.bff.api.model.GetTeamByIdResponse
import com.schedule.bff.model.Team
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

interface IScheduleService {
    fun getTeamById(id: Long, token: String): Team
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
}