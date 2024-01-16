package com.teamsparta.mytodoapp.domain.todo.repository

import com.teamsparta.mytodoapp.domain.todo.model.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<TodoEntity, Long> {
    fun findAllByOrderByCreateAtDesc(): List<TodoEntity>
}