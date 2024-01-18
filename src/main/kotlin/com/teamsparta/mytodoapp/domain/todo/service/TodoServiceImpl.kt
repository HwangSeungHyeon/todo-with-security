package com.teamsparta.mytodoapp.domain.todo.service

import com.teamsparta.mytodoapp.domain.exception.ModelNotFoundException
import com.teamsparta.mytodoapp.domain.todo.dto.request.CreateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.request.UpdateTodoDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.DetailResponseDto
import com.teamsparta.mytodoapp.domain.todo.dto.response.TodoResponseDto
import com.teamsparta.mytodoapp.domain.todo.model.TodoEntity
import com.teamsparta.mytodoapp.domain.todo.repository.TodoRepository
import com.teamsparta.mytodoapp.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
): TodoService {
    override fun getTodo(
        todoId: Long
    ): DetailResponseDto {
        return todoRepository
            .findByIdOrNull(todoId)
            ?.let { DetailResponseDto.toDetailResponse(it) } //null이 아닐 경우 여기 실행
            ?: throw ModelNotFoundException("todo", todoId) //null일 경우 여기 실행
    }

    override fun getTodoList(): List<TodoResponseDto> {
        return todoRepository
            .findAllByOrderByCreateAtDesc()
            .map { TodoResponseDto.toResponse(it) }
    }

    override fun createTodo(
        createTodoDto: CreateTodoDto,
        userPrincipal: UserPrincipal
    ): TodoResponseDto {
        val entity = todoRepository.save(TodoEntity.toEntity(createTodoDto, userPrincipal))
        return TodoResponseDto.toResponse(entity)
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
            .let { TodoResponseDto.toResponse(it) }
    }

    @Transactional
    override fun updateStatus(
        todoId: Long
    ): TodoResponseDto {
        val entity = todoRepository.findByIdOrNull(todoId)
            ?: throw ModelNotFoundException("todo", todoId)

        return entity
            .apply { isComplete() }
            .let { TodoResponseDto.toResponse(it) }
    }

    override fun deleteTodo(
        todoId: Long
    ){
        todoRepository.findByIdOrNull(todoId)
            ?.let { todoRepository.delete(it) }
            ?: throw ModelNotFoundException("todo", todoId)
    }

    override fun getUserId(todoId: Long) = todoRepository.findByIdOrNull(todoId)?.userId?:throw ModelNotFoundException("Todo", todoId)
}