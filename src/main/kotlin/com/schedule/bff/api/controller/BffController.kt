package com.schedule.bff.api.controller

import com.schedule.bff.api.model.GetTeamResponse
import com.schedule.bff.api.model.UserWithAvatar
import com.schedule.bff.client.model.User
import com.schedule.bff.service.AvatarService
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
    val extractTokenService: ExtractTokenService,
    val avatarService: AvatarService
) {
    @GetMapping("/team/{teamId}")
    fun getTeamById(
        @PathVariable teamId: Long,
        request: HttpServletRequest
    ): ResponseEntity<GetTeamResponse> {
        val token = extractTokenService.extract(request)
        val team = scheduleService.getTeamById(teamId, token)
        val teamAvatar = avatarService.getTeamAvatarByTeamId(teamId)
        val members = userService.getUsersListByIds(team.membersIds)
        val avatars = avatarService.getAvatarsByIds(team.membersIds)
        val usersWithAvatars = avatars.map { a -> userWithAvatar(members.find { u -> u.id == a.userId }!!, a.srcPath) }
        return ResponseEntity.ok().body(
            GetTeamResponse(
                team.id,
                team.name,
                team.creationDate,
                team.adminId,
                usersWithAvatars,
                team.color,
                teamAvatar.srcPath
            )
        )
    }

    fun userWithAvatar(user: User, avatar: String): UserWithAvatar {
        return UserWithAvatar(
            user.id,
            user.login,
            user.creationDate,
            user.email,
            user.confirmed,
            user.description,
            avatar
        )
    }
}