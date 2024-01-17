package com.teamsparta.mytodoapp.domain.comment.service

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.infra.security.UserPrincipal

interface CommentService {
    fun addComment(todoId: Long, addCommentDto: AddCommentDto, userPrincipal: UserPrincipal): CommentResponseDto

    fun updateComment(todoId: Long, commentId: Long, updateCommentDto: UpdateCommentDto): CommentResponseDto

    fun deleteComment(todoId: Long, commentId: Long)

    fun getUserId(todoId: Long, commentId: Long): Long
}