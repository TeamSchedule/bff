package com.schedule.bff.api.model

import com.schedule.bff.model.Avatar

data class GetAvatarsByIdsResponse(
    val avatars: List<Avatar>
)