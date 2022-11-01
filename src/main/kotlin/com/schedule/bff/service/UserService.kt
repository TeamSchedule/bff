package com.schedule.bff.service

import com.schedule.bff.api.model.GetUsersByIdsResponse
import com.schedule.bff.model.User
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

interface IUserService {
    fun getUsersListByIds(ids: List<Long>): List<User>
}

@Service
class UserService(
    @Qualifier("userRestTemplate") private val userRestTemplate: RestTemplate
) : IUserService {
    override fun getUsersListByIds(ids: List<Long>): List<User> {
        return userRestTemplate
            .getForEntity<GetUsersByIdsResponse>("/user/{ids}", ids.joinToString())
            .body
            ?.users ?: listOf()
    }
}