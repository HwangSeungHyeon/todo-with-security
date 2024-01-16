package com.teamsparta.mytodoapp.domain.todo.service

import com.teamsparta.mytodoapp.domain.todo.dto.request.CreateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateStatusDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.TodoResponseDto

interface TodoService {
    fun getTodo(todoId: Long): TodoResponseDto
    fun getTodoList(): List<TodoResponseDto>
    fun createTodo(createTodoDto: CreateTodoDto): TodoResponseDto
    fun updateTodo(todoId: Long, updateTodoDto: UpdateTodoDto): TodoResponseDto
    fun updateStatus(todoId: Long, updateStatusDto: UpdateStatusDto): TodoResponseDto
    fun deleteTodo(todoId: Long)
}