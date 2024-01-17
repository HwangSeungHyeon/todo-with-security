package com.teamsparta.mytodoapp.domain.exception

data class ForbiddenException(
    override val message: String
) : RuntimeException(message)

