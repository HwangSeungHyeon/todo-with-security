package com.teamsparta.mytodoapp.domain.comment.controller

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.domain.comment.service.CommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    @Operation(summary = "댓글 추가", description = "댓글을 추가합니다.")
    @PostMapping
    fun addComment(
        @PathVariable todoId: Long,
        @RequestBody addCommentDto: AddCommentDto
    ): ResponseEntity<CommentResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(todoId, addCommentDto))
    }

    @Operation(summary = "선택한 댓글 수정", description = "선택한 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentDto: UpdateCommentDto
    ): ResponseEntity<CommentResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentDto))
    }

    @Operation(summary = "선택한 댓글 삭제", description = "선택한 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long
    ): ResponseEntity<CommentResponseDto>{
        commentService.deleteComment(todoId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}