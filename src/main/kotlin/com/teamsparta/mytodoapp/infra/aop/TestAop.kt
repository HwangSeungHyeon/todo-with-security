package com.teamsparta.mytodoapp.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component


//관점(Aspect) = 횡단 관심사(부가기능)를 모듈화한 것
//@Aspect //Aspect를 정의할 때 사용, 클래스 자체가 관점(Aspect)이 된다. = Advice가 적혀있는 객체
//@Component //Bean으로 등록하기 위해서 Component 어노테이션 사용
class TestAop {

    //@Around = JoinPoint를 기준으로, Advice가 언제 동작할지를 정의, 메서드 실행 전후로 동작
    //@Around("execution(* com.teamsparta.mytodoapp.domain.todo.service.TodoService.*(..))") //todoservice 하위 모든 내용에 적용됨
    @Around("execution(* com.teamsparta.mytodoapp.domain.todo.service.TodoService.getTodo(..))") //getTodo 메소드에만 적용됨
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint){ //ProceedingJoinPoint = AOP가 적용되는 메서드
        println("AOP START!!!")
        //JoinPoint = Aspect가 적용될 수 있는 위치
        joinPoint.proceed() //joinPoint를 기준으로 실행됨
        println("AOP END!!!")
    }
}