package com.teamsparta.mytodoapp.domain.todo.model

import com.teamsparta.mytodoapp.domain.comment.model.CommentEntity
import com.teamsparta.mytodoapp.domain.todo.dto.request.CreateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateTodoDto
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "todo2")
class TodoEntity private constructor(
    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @OneToMany(mappedBy = "todoId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<CommentEntity> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var todoId: Long? = null

    @CreatedBy
    @Column(name = "create_name")
    var createName: String? = null

    @CreatedDate
    @Column(name = "create_at")
    var createAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null

    @Column(name = "status")
    var status: Boolean = false

    fun update(updateTodoDto: UpdateTodoDto){
        title = updateTodoDto.title
        content = updateTodoDto.content
    }

    fun isComplete(){
        status = true
    }

    companion object{
        fun toEntity(
            createTodoDto: CreateTodoDto
        ): TodoEntity{
            return TodoEntity(
                title = createTodoDto.title,
                content = createTodoDto.content
            )
        }
    }
}