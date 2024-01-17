package com.teamsparta.mytodoapp.infra.security.jwt

import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val tokenProvider: JwtProvider
) : OncePerRequestFilter() {

    companion object{
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if(jwt != null){
            tokenProvider.validateToken(jwt)
                .onSuccess {
                    val principal = UserPrincipal.setPrincipal(it)

                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )

                    SecurityContextHolder.getContext().authentication = authentication
                }
                .onFailure {

                }
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String?{
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null

        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}