package com.teamsparta.mytodoapp.domain.comment.dto.response

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.model.CommentEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "댓글 CRUD에 대한 반응을 전달하는 객체")
data class CommentResponseDto (
    @Schema(description = "댓글 PK", example = "0")
    val commentId: Long,

    @Schema(description = "댓글 내용", example = "댓글")
    val content: String,

    @Schema(description = "댓글 작성자", example = "황승현")
    val createName: String,

    @Schema(description = "댓글 작성일")
    val createAt: LocalDateTime,

    @Schema(description = "댓글 수정일")
    val updateAt: LocalDateTime
){
    companion object{
        fun toResponse(
            commentEntity: CommentEntity
        ): CommentResponseDto {
            return CommentResponseDto(
                commentId = commentEntity.commentId!!,
                content = commentEntity.content,
                createName = commentEntity.createName!!,
                createAt = commentEntity.createdAt!!,
                updateAt = commentEntity.updateAt!!
            )
        }
    }
}
