package com.teamsparta.mytodoapp.domain.comment.service

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import com.teamsparta.mytodoapp.domain.comment.model.CommentEntity
import com.teamsparta.mytodoapp.domain.comment.repository.CommentRepository
import com.teamsparta.mytodoapp.domain.exception.ModelNotFoundException
import com.teamsparta.mytodoapp.domain.todo.repository.TodoRepository
import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service //CommentServiceImpl를 Service Layer를 담당하는 bean으로 설정
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
): CommentService {

    @Transactional
    override fun addComment(
        todoId: Long,
        addCommentDto: AddCommentDto,
        userPrincipal: UserPrincipal
    ): CommentResponseDto {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        val comment = CommentEntity.toEntity(todoId, addCommentDto, userPrincipal)
        todo.comments.add(comment)

        val response = CommentResponseDto.toResponse(commentRepository.save(comment))
        return response
    }

    @Transactional
    override fun updateComment(
        todoId: Long,
        commentId: Long,
        updateCommentDto: UpdateCommentDto
    ): CommentResponseDto {
        val comment = commentRepository.findByTodoIdAndCommentId(todoId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        comment.update(updateCommentDto)
        return CommentResponseDto.toResponse(comment)
    }

    @Transactional
    override fun deleteComment(
        todoId: Long,
        commentId: Long
    ){
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = commentRepository.findByTodoIdAndCommentId(todoId, commentId) ?: throw ModelNotFoundException("Comment", commentId)
        todo.comments.remove(comment)
    }

    override fun getUserId(todoId: Long, commentId: Long) = commentRepository.findByTodoIdAndCommentId(todoId, commentId)?.userId?:throw ModelNotFoundException("Comment", commentId)
}