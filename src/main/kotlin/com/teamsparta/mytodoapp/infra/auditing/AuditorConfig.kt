package com.teamsparta.mytodoapp.infra.auditing

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import java.util.*

@Configuration
class AuditorConfig : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("황승현") //이름 자동으로 넣어줌
    }
}