package com.teamsparta.mytodoapp.infra.aop
@Target(AnnotationTarget.FUNCTION) //어노테이션이 적용될 대상을 메소드로 설정
@Retention(AnnotationRetention.RUNTIME) //런타임 시점에서 어노테이션 사용 가능
annotation class StopWatch()
