package com.schedule.bff.service

import com.schedule.bff.client.model.UsersAvatarsDto
import com.schedule.bff.client.model.TeamAvatar
import com.schedule.bff.client.model.UserAvatar
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

interface IAvatarService {
    fun getAvatarsByIds(id: List<Long>): List<UserAvatar>
    fun getTeamAvatarByTeamId(id: Long): TeamAvatar
}

@Service
class AvatarService(
    @Qualifier("avatarRestTemplate") private val avatarRestTemplate: RestTemplate
) : IAvatarService {
    override fun getAvatarsByIds(ids: List<Long>): List<UserAvatar> {
        return avatarRestTemplate
            .getForEntity<UsersAvatarsDto>(
                "/list?usersIds={usersIds}", mapOf("usersIds" to ids.joinToString(","))
            )
            .body!!
            .avatars
    }

    override fun getTeamAvatarByTeamId(id: Long): TeamAvatar {
        return avatarRestTemplate
            .getForEntity<TeamAvatar>("/teams/{id}", id)
            .body!!
    }
}