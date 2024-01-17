package com.teamsparta.mytodoapp.infra.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val userId: Long,
    val email: String,
    val name: String,
    val authorities: Collection<GrantedAuthority>
){
    companion object{
        fun setPrincipal(jwt: Jws<Claims>): UserPrincipal{
            val roles = setOf(jwt.payload.get("role", String::class.java))

            return UserPrincipal(
                userId = jwt.payload.subject.toLong(),
                email = jwt.payload.get("email", String::class.java),
                name = jwt.payload.get("name", String::class.java),
                authorities = roles.map { SimpleGrantedAuthority("ROLE_$it") }
            )
        }
    }
}
