package com.teamsparta.mytodoapp.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Component //Bean으로 등록하기 위해서 Component 어노테이션 사용
@Aspect //Aspect를 정의할 때 사용, 클래스 자체가 관점(Aspect)이 된다. = Advice가 적혀있는 객체
class StopWatchAspect {

    private val logger = LoggerFactory.getLogger("Execution Time Logger")
    @Around("@annotation(com.teamsparta.mytodoapp.infra.aop.StopWatch)") //얘를 달아야 아래에 적힌 advice를 실행
    fun run(joinPoint: ProceedingJoinPoint){
        val stopWatch = StopWatch()

        stopWatch.start() //스탑워치 시작
        joinPoint.proceed()
        stopWatch.stop() //스탑워치 끝
        
        val methodName = joinPoint.signature.name //실행한 메소드 이름
        val methodArguments = joinPoint.args //실행한 메소드 argument를 배열 형태로 알려줌

        val timeElapsedMs = stopWatch.totalTimeMillis //프로그램 실행 시간이 밀리초 단위로 기록
        logger.info("Method Name: $methodName | Arguments: ${methodArguments.joinToString { ", " }} | Execution Time: ${timeElapsedMs}ms") //로그에 걸린 시간 출력
    }
}