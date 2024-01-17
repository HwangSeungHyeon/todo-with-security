package com.teamsparta.mytodoapp.domain.todo.controller

import com.teamsparta.mytodoapp.domain.exception.ForbiddenException
import com.teamsparta.mytodoapp.domain.todo.dto.request.CreateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.DetailResponseDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.TodoResponseDto
import com.teamsparta.mytodoapp.domain.todo.service.TodoService
import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "todos", description = "할 일 API")
@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {
    @Operation(summary = "할 일 목록 조회", description = "할 일 목록을 조회합니다.")
    @GetMapping
    fun getTodoList(): ResponseEntity<List<TodoResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoList())
    }

    @Operation(summary = "선택한 할 일 조회", description = "선택한 할 일 카드를 조회합니다.")
    @GetMapping("/{todoId}")
    fun getTodo(
        @PathVariable todoId: Long,
    ): ResponseEntity<DetailResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodo(todoId))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "할 일 작성", description = "할 일을 작성합니다.")
    @PostMapping
    fun createTodo(
        @Valid @RequestBody createTodoDto: CreateTodoDto,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<TodoResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoDto, userPrincipal))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "선택한 할 일 수정", description = "선택한 할 일을 수정합니다.")
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @Valid @RequestBody updateTodoDto: UpdateTodoDto,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<TodoResponseDto>{
        if (todoService.getUserId(todoId) != userPrincipal.userId) throw ForbiddenException("수정 권한이 없습니다.")

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoDto))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "선택한 할 일 상태 수정", description = "선택한 할 일의 상태를 수정합니다.")
    @PatchMapping("/{todoId}")
    fun updateStatus(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<TodoResponseDto>{
        if (todoService.getUserId(todoId) != userPrincipal.userId) throw ForbiddenException("수정 권한이 없습니다.")

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateStatus(todoId))
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Operation(summary = "선택한 할 일 삭제", description = "선택한 할 일을 삭제합니다.")
    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit>{
        if (todoService.getUserId(todoId) != userPrincipal.userId) throw ForbiddenException("삭제 권한이 없습니다.")

        todoService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}