package com.schedule.bff.client.model

data class User(
    val id: Long,
    val login: String,
    val creationDate: String,
    val email: String,
    val confirmed: Boolean,
    val description: String
)