package com.teamsparta.mytodoapp.domain.comment.model

import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "comment2")
class CommentEntity(
    @Column(name = "content")
    var content: String,

    @Column(name = "todo_id")
    val todoId: Long
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    var commentId: Long? = null

    @CreatedBy
    @Column(name = "create_name")
    var createName: String? = null

    @CreatedDate
    @Column(name = "create_at")
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null

    fun update(
        updateComment: UpdateCommentDto
    ){
        this.content = updateComment.content
    }

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