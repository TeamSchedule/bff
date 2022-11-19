package com.schedule.bff.api.model

import com.schedule.bff.client.model.User

data class GetUsersByIdsResponse(
    val users: List<User>
)