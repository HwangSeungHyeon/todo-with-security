package com.teamsparta.mytodoapp.domain.todo.service

import com.teamsparta.mytodoapp.domain.exception.ModelNotFoundException
import com.teamsparta.mytodoapp.domain.todo.dto.request.CreateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateStatusDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.TodoResponseDto
import com.teamsparta.mytodoapp.domain.todo.model.TodoEntity
import com.teamsparta.mytodoapp.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
): TodoService {
    override fun getTodo(
        todoId: Long
    ): TodoResponseDto {
        return todoRepository
            .findByIdOrNull(todoId)
            ?.let { TodoEntity.toResponseDto(it) } //null이 아닐 경우 여기 실행
            ?: throw ModelNotFoundException("todo", todoId) //null일 경우 여기 실행
    }

    override fun getTodoList(): List<TodoResponseDto> {
        return todoRepository
            .findAllByOrderByCreateAtDesc()
            .map { TodoEntity.toResponseDto(it) }
    }

    override fun createTodo(createTodoDto: CreateTodoDto): TodoResponseDto {
        val entity = todoRepository.save(TodoEntity.toEntity(createTodoDto))
        return TodoEntity.toResponseDto(entity)
    }

    @Transactional
    override fun updateTodo(
        todoId: Long,
        updateTodoDto: UpdateTodoDto
    ): TodoResponseDto {
        val entity = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo", todoId)

        return entity
            .apply { update(updateTodoDto) }
            .let { TodoEntity.toResponseDto(it) }
    }

    @Transactional
    override fun updateStatus(
        todoId: Long,
        updateStatusDto: UpdateStatusDto
    ): TodoResponseDto {
        val entity = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo", todoId)

        return entity
            .apply { changeStatus(updateStatusDto) }
            .let { TodoEntity.toResponseDto(it) }
    }

    override fun deleteTodo(
        todoId: Long
    ){
        todoRepository.findByIdOrNull(todoId)
            ?.let { todoRepository.delete(it) }
            ?: throw ModelNotFoundException("todo", todoId)
    }
}