package com.teamsparta.mytodoapp.domain.user.repository

import com.teamsparta.mytodoapp.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<UserEntity, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): UserEntity?
}