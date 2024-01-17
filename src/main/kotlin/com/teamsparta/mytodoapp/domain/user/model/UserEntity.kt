package com.teamsparta.mytodoapp.domain.user.model

import com.teamsparta.mytodoapp.domain.user.dto.request.UserSignUpDto
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "app_user2")
class UserEntity private constructor(
    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: ROLE
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null

    companion object{
        fun toEntity(
            userSignUpDto: UserSignUpDto,
            passwordEncoder: PasswordEncoder
        ): UserEntity{
            return UserEntity(
                name = userSignUpDto.name,
                email = userSignUpDto.email,
                password = passwordEncoder.encode(userSignUpDto.password),
                role = ROLE.MEMBER
            )
        }
    }
}