package com.teamsparta.mytodoapp.domain.user.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

@Schema(description = "로그인할 때 입력받은 값을 전달하는 객체")
data class UserLoginDto(
    @field:Email(message = "올바른 이메일 주소를 입력해주세요")
    @field:NotNull(message = "이메일은 필수 입력값입니다.")
    @Schema(description = "회원가입할 때 사용할 이메일", example = "test@gmail.com")
    val email: String,

    @field:NotBlank(message = "비밀번호를 입력해주세요")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9!@#\$%^&*]{8,25}$",
        message = "비밀번호는 영어, 숫자, 특수문자로 이루어진 8~25자리 문자열로 입력해야 합니다."
    )
    @Schema(description = "회원가입할 때 사용할 비밀번호", example = "12345678!")
    val password: String
)
