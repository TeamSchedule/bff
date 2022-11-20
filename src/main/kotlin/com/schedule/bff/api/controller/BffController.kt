package com.schedule.bff.api.controller

import com.schedule.bff.api.model.*
import com.schedule.bff.client.model.TeamInvite
import com.schedule.bff.client.model.User
import com.schedule.bff.service.AvatarService
import com.schedule.bff.service.IScheduleService
import com.schedule.bff.service.IUserService
import com.schedule.bff.service.jwt.ExtractTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/bff")
class BffController(
    val userService: IUserService,
    val scheduleService: IScheduleService,
    val extractTokenService: ExtractTokenService,
    val avatarService: AvatarService
) {
    @GetMapping("/team/invite")
    fun getTeamInvites(
        @RequestParam(name = "criteria") criteria: TeamInviteCriteria,
        @RequestParam(name = "status") status: TeamInviteStatus,
        @RequestParam(name = "teamId", required = false) teamId: Optional<Long>,
        request: HttpServletRequest
    ): ResponseEntity<GetTeamInvitesResponse> {
        val token = extractTokenService.extract(request)
        val teamInvites = scheduleService.getTeamInvite(token, criteria, status, teamId)
            .map { teamInvite -> teamInviteWithUsers(teamInvite) }
        return ResponseEntity.ok().body(
            GetTeamInvitesResponse(teamInvites)
        )
    }

    @GetMapping("/team/{teamId}")
    fun getTeamById(
        @PathVariable teamId: Long,
        request: HttpServletRequest
    ): ResponseEntity<GetTeamResponse> {
        val token = extractTokenService.extract(request)
        val team = scheduleService.getTeamById(teamId, token)
        val teamAvatar = avatarService.getTeamAvatarByTeamId(teamId)
        val usersWithAvatars = usersWithAvatars(team.membersIds)
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

    fun usersWithAvatars(usersIds: List<Long>): List<UserWithAvatar> {
        val members = userService.getUsersListByIds(usersIds)
        val avatars = avatarService.getAvatarsByIds(usersIds)
        return avatars.map { a -> userWithAvatar(members.find { u -> u.id == a.userId }!!, a.srcPath) }
    }

    fun teamInviteWithUsers(teamInvite: TeamInvite): TeamInviteWithUsers {
        val users = usersWithAvatars(listOf(teamInvite.invitingId, teamInvite.invitedId))
        return TeamInviteWithUsers(
            teamInvite.id,
            users[0],
            users[1],
            teamInvite.date,
            teamInvite.inviteStatus,
            teamInvite.team
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