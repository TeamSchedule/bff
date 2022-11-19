package com.schedule.bff.api.model

data class UserWithAvatar(
    val id: Long,
    val login: String,
    val creationDate: String,
    val email: String,
    val confirmed: Boolean,
    val description: String,
    val avatar: String
)