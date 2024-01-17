package com.teamsparta.mytodoapp.domain.user.dto.response

import com.teamsparta.mytodoapp.domain.user.model.UserEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인이 성공했을 때 응답을 전달하는 객체")
data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String
)