package com.schedule.bff.service.jwt

import javax.servlet.http.HttpServletRequest

interface ExtractTokenService {
    fun extract(request: HttpServletRequest): String
}