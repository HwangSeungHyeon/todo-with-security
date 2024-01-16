package com.teamsparta.mytodoapp.domain.comment.service

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.domain.comment.model.CommentEntity
import com.teamsparta.mytodoapp.domain.comment.repository.CommentRepository
import com.teamsparta.mytodoapp.domain.exception.ModelNotFoundException
import com.teamsparta.mytodoapp.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service //CommentServiceImpl를 Service Layer를 담당하는 bean으로 설정
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
): CommentService {

    @Transactional
    override fun addComment(todoId: Long, addCommentDto: AddCommentDto): CommentResponseDto {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        val comment = CommentResponseDto.toEntity(todoId, addCommentDto)
        todo.comments.add(comment)

        val response = CommentEntity.toResponse(commentRepository.save(comment))
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
        return CommentEntity.toResponse(comment)
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
}