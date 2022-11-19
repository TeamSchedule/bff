package com.schedule.bff.api.model

import com.schedule.bff.client.model.UserAvatar

data class GetAvatarsByIdsResponse(
    val userAvatars: List<UserAvatar>
)