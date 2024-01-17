package com.teamsparta.mytodoapp.infra.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.teamsparta.mytodoapp.domain.exception.dto.ErrorResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.jvm.Throws

@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
    private val objectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.characterEncoding = "UTF-8"

        val objectMapper = ObjectMapper()

        val errorResponse = objectMapper.writeValueAsString(ErrorResponseDto("No permission to run API"))
        response.writer.write(errorResponse)
    }
}