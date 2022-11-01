package com.schedule.bff.service.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class ExtractTokenServiceImpl : ExtractTokenService {
    @Value("\${app.jwt.token.headerName}")
    private val tokenHeaderName: String? = null

    @Value("\${app.jwt.token.prefix}")
    private val tokenStart: String? = null

    override fun extract(request: HttpServletRequest): String {
        return request
            .getHeader(tokenHeaderName)
            .substring(tokenStart!!.length)
    }
}