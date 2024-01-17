package com.teamsparta.mytodoapp.infra.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.teamsparta.mytodoapp.domain.exception.dto.ErrorResponseDto
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.jvm.Throws

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.characterEncoding = "UTF-8"

        val objectMapper = ObjectMapper()

        val errorResponse = objectMapper.writeValueAsString(ErrorResponseDto("JWT verification failed"))
        response.writer.write(errorResponse)
    }
}