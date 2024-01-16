package com.teamsparta.mytodoapp.domain.comment.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "댓글을 추가할 때 입력한 정보를 전달하는 객체")
data class AddCommentDto(
    @field:NotBlank(message = "댓글을 입력해주세요.")
    @Size(min = 1, max = 1000, message = "댓글은 1자 이상 1000자 이하로 작성해주세요")
    @Schema(description = "댓글 내용", example = "댓글")
    val content: String
)
