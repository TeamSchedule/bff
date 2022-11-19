package com.schedule.bff.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {
    @Value("\${app.external.schedule.address}")
    lateinit var avatarBaseUrl: String

    @Value("\${app.external.schedule.address}")
    lateinit var scheduleBaseUrl: String

    @Value("\${app.external.user.address}")
    lateinit var userBaseUrl: String

    @Bean
    fun scheduleRestTemplate() : RestTemplate {
        return RestTemplateBuilder()
            .rootUri(scheduleBaseUrl)
            .build()
    }

    @Bean
    fun userRestTemplate() : RestTemplate {
        return RestTemplateBuilder()
            .rootUri(userBaseUrl)
            .build()
    }

    @Bean
    fun avatarRestTemplate() : RestTemplate {
        return RestTemplateBuilder()
            .rootUri(avatarBaseUrl)
            .build()
    }
}