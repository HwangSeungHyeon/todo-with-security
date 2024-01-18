package com.teamsparta.mytodoapp.domain.user.dto.response

import com.teamsparta.mytodoapp.domain.user.model.ROLE
import com.teamsparta.mytodoapp.domain.user.model.UserEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "응답을 전달하는 객체")
data class UserResponseDto(
    val userId: Long,
    val name: String,
    val email: String,
    val role: ROLE
){
    companion object{
        fun toResponse(userEntity: UserEntity): UserResponseDto{
            return UserResponseDto(
                userId = userEntity.userId!!,
                name = userEntity.name,
                email = userEntity.email,
                role = userEntity.role
            )
        }
    }
}
