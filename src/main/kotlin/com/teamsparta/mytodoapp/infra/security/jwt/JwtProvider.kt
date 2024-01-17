package com.teamsparta.mytodoapp.infra.security.jwt

import com.teamsparta.mytodoapp.domain.user.dto.request.UserLoginDto
import com.teamsparta.mytodoapp.domain.user.model.UserEntity
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.sql.Date
import java.time.Duration
import java.time.Instant

@PropertySource("classpath:jwt.yml")
@Component
class JwtProvider (
    @Value("\${secret}")
    private val secret: String, //jwt.yml 파일에 있는 값이 들어감

    @Value("\${issuer}")
    private val issuer: String,

    @Value("\${accessExpirationHour}")
    private val accessExpiration: Long,

    @Value("\${refreshExpirationHour}")
    private val refreshExpiration: Long,
){
    fun validateToken(jwt: String): Result<Jws<Claims>> {

        //runCatching 블록 내에서 에러가 발생하면 Catch하는 방법
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(userEntity: UserEntity): String{
        return generateToken(userEntity, accessExpiration)
    }

    fun generateRefreshToken(userEntity: UserEntity): String{
        return generateToken(userEntity, refreshExpiration)
    }

    private fun generateToken(
        userEntity: UserEntity,
        expirationPeriod: Long
    ): String{
        val subject = userEntity.userId.toString()
        val role = userEntity.role
        val email = userEntity.email
        val name = userEntity.name

        val claims: Claims = Jwts.claims()
            .add(mapOf("role" to role, "email" to email, "name" to name))
            .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(Duration.ofHours(expirationPeriod))))
            .claims(claims)
            .signWith(key)
            .compact()
    }
}