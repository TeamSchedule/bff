package com.schedule.bff.api.controller

import com.schedule.bff.api.model.GetTeamResponse
import com.schedule.bff.service.IScheduleService
import com.schedule.bff.service.IUserService
import com.schedule.bff.service.jwt.ExtractTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/bff")
class BffController(
    val userService: IUserService,
    val scheduleService: IScheduleService,
    val extractTokenService: ExtractTokenService
) {
    @GetMapping("/team/{id}")
    fun getTeamById(
        @PathVariable id: Long,
        request: HttpServletRequest
    ): ResponseEntity<GetTeamResponse> {
        val token = extractTokenService.extract(request)
        val team = scheduleService.getTeamById(id, token)
        val members = userService.getUsersListByIds(team.membersIds)
        return ResponseEntity.ok().body(
            GetTeamResponse(
                team.id,
                team.name,
                team.creationDate,
                team.adminId,
                members,
                team.color
            )
        )
    }
}