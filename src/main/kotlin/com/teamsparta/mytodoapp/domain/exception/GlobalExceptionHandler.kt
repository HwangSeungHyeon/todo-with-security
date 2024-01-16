package com.teamsparta.mytodoapp.domain.exception

import com.teamsparta.mytodoapp.domain.exception.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/*
* Spring의 Web Layer의 일부
* 하위 Layer에서 발생한 예외들을 처리하여 적절한 응답(Response)을 주는 역할
*/
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    //ModelNotFoundException: 하위 layer에서 id로 검색에 실패했을 때 발생하는 에러
    //에러가 발생하면 ResponseEntity를 반환한다.
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponseDto>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto(e.message))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponseDto>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto(e.message))
    }
}