package com.schedule.bff.api.converter

import com.schedule.bff.api.model.GetTeamInviteCriteria
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StringToGetTeamInviteCriteriaConverter : Converter<String, GetTeamInviteCriteria> {
    override fun convert(source: String): GetTeamInviteCriteria {
        return GetTeamInviteCriteria.valueOf(source.uppercase())
    }
}