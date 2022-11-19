package com.schedule.bff.service

import com.schedule.bff.api.model.GetAvatarsByIdsResponse
import com.schedule.bff.model.Avatar
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponentsBuilder

interface IAvatarService {
    fun getAvatarsByIds(ids: List<Long>): List<Avatar>
}

@Service
class AvatarService(
    @Qualifier("avatarRestTemplate") private val avatarRestTemplate: RestTemplate
) : IAvatarService {
    override fun getAvatarsByIds(ids: List<Long>): List<Avatar> {
        val uri = UriComponentsBuilder
            .newInstance()
            .path("/list")
            .queryParam("usersIds", ids.joinToString(","))
            .build()
            .toUri()
        return avatarRestTemplate
            .getForEntity<GetAvatarsByIdsResponse>(uri)
            .body!!
            .avatars
    }
}