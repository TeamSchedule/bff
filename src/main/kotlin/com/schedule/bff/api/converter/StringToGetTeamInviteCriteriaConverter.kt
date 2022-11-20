package com.schedule.bff.api.converter

import com.schedule.bff.api.model.TeamInviteCriteria
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StringToGetTeamInviteCriteriaConverter : Converter<String, TeamInviteCriteria> {
    override fun convert(source: String): TeamInviteCriteria {
        return TeamInviteCriteria.valueOf(source.uppercase())
    }
}