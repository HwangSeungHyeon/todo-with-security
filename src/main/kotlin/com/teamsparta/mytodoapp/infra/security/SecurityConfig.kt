package com.teamsparta.mytodoapp.infra.security

import com.teamsparta.mytodoapp.infra.security.jwt.JwtAuthenticationEntryPoint
import com.teamsparta.mytodoapp.infra.security.jwt.JwtAuthenticationFilter
import com.teamsparta.mytodoapp.infra.security.jwt.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
) {
    private val allowedUrls = arrayOf(
        "/", "/swagger-ui/**", "/v3/**",
        "/todos/**"
    )

    private val anonymousUrls = arrayOf(
        "/signup", "/login",
    )

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            //filter chain에서 DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외함
            .formLogin { it.disable() }

            //filter chain에서 BasicAuthenticationFilter를 꺼버림
            .httpBasic { it.disable() }
            
            //filter chain에서 CsrfFilter 제외함
            .csrf { it.disable() } //토큰 기반 인증방식을 사용하면 csrf를 어느정도 막을 수 있다

            .authorizeHttpRequests {
                it.requestMatchers(*allowedUrls).permitAll()
                    .requestMatchers(*anonymousUrls).anonymous() //익명 사용자만 접근 가능
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .exceptionHandling {
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            }
        return http.build()
    }
}