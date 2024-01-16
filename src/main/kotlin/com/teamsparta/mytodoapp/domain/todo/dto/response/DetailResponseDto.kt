package com.teamsparta.mytodoapp.domain.todo.dto.response

import com.teamsparta.mytodoapp.domain.comment.model.CommentEntity
import com.teamsparta.mytodoapp.domain.todo.model.TodoEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "상세 페이지 조회에 대한 반응을 전달하는 객체")
data class DetailResponseDto(
    @Schema(description = "할 일 카드 PK", example = "0")
    val todoId: Long,

    @Schema(description = "작성한 할 일 카드 제목", example = "제목")
    val title: String,

    @Schema(description = "작성한 할 일 내용", example = "내용")
    val content: String,

    @Schema(description = "작성자 이름", example = "황승현")
    val createName: String,

    @Schema(description = "작성 날짜")
    val createAt: LocalDateTime,

    @Schema(description = "수정 날짜")
    val updateAt: LocalDateTime,

    @Schema(description = "완료 상태")
    val status: Boolean,

    @Schema(description = "할 일에 달린 댓글")
    val comments: MutableList<CommentEntity>
){
    companion object{
        fun toDetailResponse(
            todoEntity: TodoEntity
        ): DetailResponseDto{
            return DetailResponseDto(
                todoId = todoEntity.todoId!!,
                title = todoEntity.title,
                content = todoEntity.content,
                createName = todoEntity.createName!!,
                createAt = todoEntity.createAt!!,
                updateAt = todoEntity.updateAt!!,
                status = todoEntity.status,
                comments = todoEntity.comments
            )
        }
    }
}
