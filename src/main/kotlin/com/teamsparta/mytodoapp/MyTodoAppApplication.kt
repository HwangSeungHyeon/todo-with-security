package com.teamsparta.mytodoapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing //AuditingEntityListener 기능 사용하기 위해서 추가
@EnableAspectJAutoProxy // AOP 기능을 사용하기 위해서 추가
@SpringBootApplication
class MyTodoAppApplication

fun main(args: Array<String>) {
    runApplication<MyTodoAppApplication>(*args)
}