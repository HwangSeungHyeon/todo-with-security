package com.teamsparta.mytodoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing //AuditingEntityListener 기능 사용하기 위해서 추가
class MyTodoAppApplication

fun main(args: Array<String>) {
    runApplication<MyTodoAppApplication>(*args)
}
