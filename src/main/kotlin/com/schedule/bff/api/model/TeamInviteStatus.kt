package com.schedule.bff.api.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.util.stream.Stream

enum class TeamInviteStatus(@get:JsonValue val code: String) {
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    CLOSED("closed"),
    OPEN("open");

    companion object {
        @JsonCreator
        fun decode(code: String): TeamInviteStatus? {
            return Stream.of(*values())
                .filter { targetEnum: TeamInviteStatus ->
                    targetEnum.code == code.lowercase()
                }
                .findFirst()
                .orElse(null)
        }
    }
}