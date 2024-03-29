package com.teamsparta.mytodoapp.domain.todo.service

import com.teamsparta.mytodoapp.domain.todo.dto.request.CreateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.DetailResponseDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.TodoResponseDto
import com.teamsparta.mytodoapp.infra.security.UserPrincipal

interface TodoService {
    fun getTodo(todoId: Long): DetailResponseDto
    fun getTodoList(): List<TodoResponseDto>
    fun createTodo(createTodoDto: CreateTodoDto, userPrincipal: UserPrincipal): TodoResponseDto
    fun updateTodo(todoId: Long, updateTodoDto: UpdateTodoDto): TodoResponseDto
    fun updateStatus(todoId: Long): TodoResponseDto
    fun deleteTodo(todoId: Long)
    fun getUserId(todoId: Long): Long
}