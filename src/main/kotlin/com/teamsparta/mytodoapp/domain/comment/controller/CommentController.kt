package com.teamsparta.mytodoapp.domain.comment.controller

import com.teamsparta.mytodoapp.domain.comment.dto.request.AddCommentDto
import com.teamsparta.mytodoapp.domain.comment.dto.response.CommentResponseDto
import com.teamsparta.mytodoapp.domain.comment.dto.request.UpdateCommentDto
import com.teamsparta.mytodoapp.domain.comment.service.CommentService
import com.teamsparta.mytodoapp.domain.exception.ForbiddenException
import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "comments", description = "댓글 API")
@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "댓글 추가", description = "댓글을 추가합니다.")
    @PostMapping
    fun addComment(
        @PathVariable todoId: Long,
        @Valid @RequestBody addCommentDto: AddCommentDto,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CommentResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(todoId, addCommentDto, userPrincipal))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "선택한 댓글 수정", description = "선택한 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @Valid @RequestBody updateCommentDto: UpdateCommentDto,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CommentResponseDto>{
        if (commentService.getUserId(todoId, commentId) != userPrincipal.userId) throw ForbiddenException("수정 권한이 없습니다.")

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentDto))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "선택한 댓글 삭제", description = "선택한 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CommentResponseDto>{
        if (commentService.getUserId(todoId, commentId) != userPrincipal.userId) throw ForbiddenException("삭제 권한이 없습니다.")

        commentService.deleteComment(todoId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}