package com.teamsparta.mytodoapp.domain.comment.model

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "comment2")
class CommentEntity private constructor(
    @Column(name = "content")
    var content: String,

    @Column(name = "todo_id")
    val todoId: Long,

    @Column(name = "user_id")
    val userId: Long
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
        fun toEntity(
            todoId: Long,
            addCommentRequestDto: AddCommentDto,
            userPrincipal: UserPrincipal
        ): CommentEntity {
            return CommentEntity(
                content = addCommentRequestDto.content,
                todoId = todoId,
                userId = userPrincipal.userId
            )
        }
    }
}