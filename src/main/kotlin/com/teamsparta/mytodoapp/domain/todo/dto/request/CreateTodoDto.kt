package com.teamsparta.mytodoapp.domain.todo.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "할 일 카드를 작성할 때 입력한 정보를 전달하는 객체")
data class CreateTodoDto (
    @field:NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하로 작성해주세요")
    @Schema(description = "할 일 제목", example = "제목")
    val title: String,

    @field:NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 1000, message = "내용은 1자 이상 1000자 이하로 작성해주세요")
    @Schema(description = "할 일 내용", example = "할 일")
    val content: String
)