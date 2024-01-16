package com.teamsparta.mytodoapp.domain.todo.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "할 일 카드 상태를 바꿀 때 입력한 값을 전달하는 객체")
data class UpdateStatusDto(
//    @field:NotBlank(message = "상태를 입력해주세요")
    val status: Boolean
)
