package com.schedule.bff.api.converter

import com.schedule.bff.api.model.TeamInviteStatus
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StringToTeamInviteStatusConverter : Converter<String, TeamInviteStatus> {
    override fun convert(source: String): TeamInviteStatus {
        return TeamInviteStatus.decode(source)!!
    }
}