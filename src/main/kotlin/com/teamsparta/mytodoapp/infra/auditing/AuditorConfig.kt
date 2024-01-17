package com.teamsparta.mytodoapp.infra.auditing

import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
class AuditorConfig : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val authentication = SecurityContextHolder.getContext().authentication

        val userPrincipal = authentication.principal as UserPrincipal


        return Optional.of(userPrincipal.name) //이름 자동으로 넣어줌
    }
}